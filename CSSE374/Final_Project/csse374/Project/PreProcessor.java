package csse374.Project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import csse374.AlgorithmResolvers.CallGraphResolveBehavior;
import csse374.AlgorithmResolvers.HeirarchyObjectResolveBehavior;
import csse374.AlgorithmResolvers.IResolveBehavior;
import csse374.AlgorithmResolvers.IStrategy;
import csse374.AlgorithmResolvers.Resolver;
import csse374.analyzers.AbstractAnalyzor;
import csse374.analyzers.SequenceAnalyzor;
import csse374.revengd.soot.SceneBuilder;

public class PreProcessor {
	private Scanner scanner;
	private Map<String, String> configMap;
	private Map<String, Boolean> optionMap;
	private Map<String, IFilter> filterMap;
	private ListMultimap<String, AbstractAnalyzor> analyzorMap;
	private List<String> pipeline;
	private SceneBuilder soot;
	private Model model;
	private Map<String, IResolveBehavior> primbehaviors;
	private Map<String, IStrategy> aggbehaviors;
	private String[] args;

	public PreProcessor(String[] args) {
		this.pipeline = new ArrayList<>();
		this.scanner = new Scanner(System.in);
		this.soot = SceneBuilder.create();
		this.configMap = new HashMap<>();
		this.optionMap = new HashMap<>();
		this.analyzorMap = ArrayListMultimap.create();
		this.filterMap = new HashMap<>();
		this.primbehaviors = new HashMap<>();
		this.aggbehaviors = new HashMap<>();
		this.args = args;
		IFilter publicf = new PublicFilter();
		IFilter privatef = new PrivateFilter();
		IFilter protectedf = new ProtectedFilter();
		addFilter("public", publicf);
		addFilter("private", privatef);
		addFilter("protected", protectedf);
		addPrimResolver("callgraph", new CallGraphResolveBehavior());
		addPrimResolver("heirarchy", new HeirarchyObjectResolveBehavior());
	}

	public void addAggResolver(String string, IStrategy r) {
		this.aggbehaviors.put(string, r);
		
	}

	public void addPrimResolver(String string, IResolveBehavior r) {
		this.primbehaviors.put(string, r);
	}

	public void process() throws IOException {
		printPrompt();
//		String command = scanner.nextLine();
		if(this.args.length != 0) {
			String command = this.args[0];
		}
		String command = "";
		fillconfig(command);
		addFilters();
		methodResolveSet();
		generateModel(initPipeline());
		model.execute();
	}
	
	// TODO this is the worst code ever written in the history of mankind
	private void methodResolveSet() {
		SequenceAnalyzor analyzor = (SequenceAnalyzor) this.analyzorMap.get("-sig").get(1);
		if(this.configMap.get("-agg") == null) {
			if(this.configMap.get("-methodResolver") == null) {
				analyzor.setBehavior(this.primbehaviors.get("callgraph"));
			} else {
				analyzor.setBehavior(this.primbehaviors.get(this.configMap.get("-methodResolver")));
			}
		} else {
			Resolver r = new Resolver();
			IStrategy strategy = this.aggbehaviors.get(this.configMap.get("-agg"));
			r.addStrategy(strategy);
			String[] strings = this.configMap.get("-methods").split(",");
			for (int i = 0; i < strings.length; i++) {
				r.addBehavior(this.primbehaviors.get(strings[i]));
			}
			analyzor.setBehavior(r);
		}
	}

	private void addFilters() {
		List<IFilter> filters = initFilter();
		for (AbstractAnalyzor val : this.analyzorMap.values()) {
			val.addFilters(filters);
		}
	}

	private void printPrompt() {
		System.out.println("Welcome to Project Analyzor!");
		System.out.println("---------------------------------------");
		System.out.println("Arguments:");
		System.out.println("---------------------------------------");
		System.out.println("Please type in your config file path or leave blank for the default path:");
	}

	private void generateModel(List<AbstractAnalyzor> analyzors) {
		Model model = new Model(configMap, analyzors);
		this.model = model;
	}

	private List<AbstractAnalyzor> initPipeline() {
		List<AbstractAnalyzor> analyzors = new ArrayList<>();
		for (String s : pipeline) {
			if (analyzorMap.containsKey(s)) {
				analyzors.addAll(analyzorMap.get(s));
			}
		}
		if (!pipeline.contains("-sig")) {
			analyzors.addAll(analyzorMap.get("uml"));
		}
		analyzors.addAll(analyzorMap.get("render"));
		return analyzors;
	}

	private List<IFilter> initFilter() {
		List<IFilter> filters = new ArrayList<>();
		if (this.configMap.get("-f") == null) {
			filters.add(filterMap.get("private"));
			return filters;
		}
		String[] tokens = this.configMap.get("-f").split(",");
		for (String s : tokens) {
			filters.add(filterMap.get(s));
		}
		return filters;
	}
	
	private void fillconfig(String command) throws IOException {
		Path filePath = null;
		if (command.length() != 0) {
			filePath = Paths.get(command);
		} else {
			filePath = Paths.get(System.getProperty("user.dir"), "configFile.txt");
		}
		Scanner s = new Scanner(filePath);
		String token = null;
		String[] split = null;
		while (s.hasNextLine()) {
			token = s.nextLine();
			split = token.split(" ");
			if (split.length == 2) {
				putResponsibly(split[0], split[1]);
			}
		}
	}

	private void putResponsibly(String key, String value) {
		if (key.equals("-pattern")) {
			for (String detect : value.split(",")) {
				this.pipeline.add(detect);
				this.configMap.put(key, value);
			}
		}
		if (this.optionMap.containsKey(key)) {
			this.pipeline.add(key);
			this.optionMap.put(key, Boolean.TRUE);
		}
		if (this.configMap.containsKey(key)) {
			this.pipeline.add(key);
			this.configMap.put(key, value);
		}
	}

	/*
	 * For adding analyzers to system.
	 */
	public void addAnalyzor(String name, AbstractAnalyzor analyzor) {
		this.analyzorMap.put(name, analyzor);
	}

	/*
	 * convinence method for adding options
	 */
	public void addOptions(String[] options) {
		for (String s : options) {
			addOption(s);
		}
	}

	/*
	 * This is distinctly -recursive -public -private etc
	 */
	public void addOption(String option) {
		this.optionMap.put(option, Boolean.FALSE);
	}

	/*
	 * This is for args that go -X y e.g. -dir /Users/blah
	 */
	public void addConfig(String name) {
		this.configMap.put(name, null);
	}

	/*
	 * convienence method for adding configs
	 */
	public void addConfigs(String[] configs) {
		for (String s : configs) {
			addConfig(s);
		}
	}

	public void addFilter(String name, IFilter filter) {
		this.filterMap.put(name, filter);
	}
}
