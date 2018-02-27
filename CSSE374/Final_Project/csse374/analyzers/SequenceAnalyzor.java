package csse374.analyzers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import csse374.AlgorithmResolvers.IResolveBehavior;
import csse374.Project.Model;
import h.item;
import soot.Body;
import soot.Hierarchy;
import soot.MethodOrMethodContext;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.ReturnStmt;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.tagkit.LineNumberTag;
import soot.tagkit.Tag;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

public class SequenceAnalyzor extends FileAnalyzor {
	private Model model;
	private Set<SootClass> sootclasses;
	private Set<String> createdClasses;
	private IResolveBehavior behaviors = null;

	public SequenceAnalyzor(File file) {
		super(file);
	}
	
	public void setBehavior(IResolveBehavior b) {
		this.behaviors = b;
	}

	@Override
	protected void fileAnalyze(Model model, File file) throws IOException {
		int depth = Integer.valueOf(model.getDepth());
		this.sootclasses = model.getSootClass();
		this.model = model;
		this.createdClasses = new HashSet<String>();
		OutputStream os = new FileOutputStream(file);
		UnitGraph stmt = model.getUnitGraph();
		String s = model.getMethodSignature().split(",")[0];
		Iterator<Unit> iter = stmt.iterator();

		os.write(("@startuml\n").getBytes());
		os.write(("skinparam linetype ortho\n").getBytes());
		sequenceAnalyzeHelper(os, iter, depth, s, "","");

		os.write(("@enduml").getBytes());
		os.close();

		prettyPrint("\n================\n", stmt);
	}

	private void sequenceAnalyzeHelper(OutputStream os, Iterator<Unit> iter, int depth, String CallingClassName,
			String returnClass,String returnType) throws IOException {
		createdClasses.add(CallingClassName);

		Unit curr;
		if (depth == 0) {
			return;
		}
		while (iter.hasNext()) {
			curr = iter.next();
			if (curr instanceof AssignStmt) {
				Value rightOp = ((AssignStmt) curr).getRightOp();
				String[] str = rightOp.toString().split(" ");
				if (str[0].equals("new")) {
					boolean present = false;
					for (SootClass e : sootclasses) {
						if (str[1].equals(e.getName())) {
							present = true;
						}
					}
					if (present) {
						if(!str[1].equals(CallingClassName)){
							os.write(("create " + str[1] + "\n").getBytes());
						}
					}
				} else {
					if (rightOp instanceof InvokeExpr) {
						evaluateInvkExpr(curr, (InvokeExpr) rightOp, os, CallingClassName, depth);
					}
				}
			} else if (curr instanceof InvokeStmt) {
				InvokeExpr expr = ((InvokeStmt) curr).getInvokeExpr();
				evaluateInvkExpr(curr, expr, os, CallingClassName, depth);
			} else if (curr instanceof ReturnStmt) {
				String[] tokens = curr.toString().split(" ");
				System.out
						.println("\n ========" + curr.toString() + "\n" + CallingClassName + " " + returnClass + "\n");
				if (tokens.length > 1) {
					if (!tokens[1].equals("$r0")) {
						String string = CallingClassName + " --> " + returnClass + " : " + tokens[1] + "\n";
						os.write(string.getBytes());
					} else {
						String string = CallingClassName + " --> " + returnClass + " : " + returnType.split("\\.")[returnType.split("\\.").length-1].toLowerCase() + "\n";
						os.write(string.getBytes());
					}
				}
			}
		}
		return;
	}

	private void evaluateInvkExpr(Unit curr, InvokeExpr invk, OutputStream os, String CallingClassName, int depth)
			throws IOException {
		InvokeExpr invkExpr = invk;
		SootMethod method = invkExpr.getMethod();
		String scName = sootClassFinder(curr);
		boolean present = false;
		for (SootClass e : sootclasses) {
			if (scName.equals(e.getName())) {
				present = true;
			}
		}
		if (present) {
			Scene scene = this.model.getScene();
			if (scene.getSootClass(scName).isAbstract() || scene.getSootClass(scName).isInterface()) {
				//method = performContextSensitivePointerAnalysis(scene, method);
				List<SootMethod> possibleMethods = resolveMethod(this.model, curr, method);
				method = possibleMethods.get(0);
				scName = method.getDeclaringClass().toString();
				possibleMethods.remove(0);
				os.write(("/'\n").getBytes());
				for(SootMethod r : possibleMethods) {
					os.write((CallingClassName + " -> " + r.getDeclaringClass().toString() + " : " + r.getName() + printParams(r) + "\n")
							.getBytes());
				}
				os.write(("'/\n").getBytes());
			}
			os.write((CallingClassName + " -> " + scName + " : " + method.getName() + printParams(method) + "\n")
					.getBytes());
			if (!method.getName().equals("<init>")) {
				os.write(("activate " + scName + "\n").getBytes());
			}
			if (!method.isPhantom()) {
				if (method.hasActiveBody()) {
					Body body = method.getActiveBody();
					UnitGraph graph = new ExceptionalUnitGraph(body);
					sequenceAnalyzeHelper(os, graph.iterator(), depth - 1, scName, CallingClassName,method.getReturnType().toString());
				}
			}
			if (!method.getName().equals("<init>")) {
				os.write(("deactivate " + scName + "\n").getBytes());
			}

		}

	}

	private List<SootMethod> resolveMethod(Model model, Unit stmt, SootMethod method) {
		return this.behaviors.resolve(stmt, method, model);
	}

	public void prettyPrintMethods(String title, SootMethod originalMethod, Iterable<SootMethod> methods) {
		System.out.println("-------------------------------------------------");
		System.out.format("%s [%s.%s(...)]:%n", title, originalMethod.getDeclaringClass().getName(),
				originalMethod.getName());
		System.out.println("-------------------------------------------------");
		methods.forEach(method -> {
			System.out.format("[%d] %s.%s(...)%n", method.getJavaSourceStartLineNumber(),
					method.getDeclaringClass().getName(), method.getName());
		});
		System.out.println("-------------------------------------------------");
	}

	private String printParams(SootMethod method) {
		List<Type> params = method.getParameterTypes();
		StringBuilder s = new StringBuilder();
		s.append(" ( ");
		for (Type e : params) {
			s.append(e.toString() + " ");
		}
		s.append(")");
		return s.toString();
	}

	// assignStmt $r0 = new java.util.random
	// staticinvoke <java.lang.system: long currentTimeMillis()>()

	// This method probably needs more rigourous testing but I can't find
	// documentation on the format of statements which means this is mostly
	// assumed correct until an error occurs
	private String sootClassFinder(Unit m) {
		String[] str = m.toString().split(" ");
		String str2 = null;
		if (str[0].equals("interfaceinvoke")) {
			int first = str[1].indexOf(".");
			str2 = str[1].substring(first + 2, str[1].length() - 1);
		} else if (str[0].equals("staticinvoke")) {
			str2 = str[1].substring(1, str[1].length() - 1);
		} else if (str[0].equals("specialinvoke") || str[0].equals("virtualinvoke")) {
			int first = str[1].indexOf(".");
			str2 = str[1].substring(first + 2, str[1].length() - 1);
		} else if (str[3].substring(0, 1).equals("<")) {
			str2 = str[3].substring(1, str[3].length() - 1);
		} else {
			int first = str[3].indexOf(".");
			str2 = str[3].substring(first + 2, str[3].length() - 1);
		}
		return str2;
	}

	// These are used for debugging
	void prettyPrint(String title, Iterable<Unit> stmts) {
		System.out.println("-------------------------------------------------");
		System.out.println(title);
		System.out.println("-------------------------------------------------");
		stmts.forEach(stmt -> {
			System.out.format("[%d] [%s] %s%n", this.getLineNumber(stmt), stmt.getClass().getName(), stmt.toString());
		});
		System.out.println("-------------------------------------------------");
	}

	int getLineNumber(Unit stmt) {
		for (Tag tag : stmt.getTags()) {
			if (tag instanceof LineNumberTag)
				return ((LineNumberTag) tag).getLineNumber();
		}
		return -1;
	}
}
