package csse374.Project;

import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import csse374.AlgorithmResolvers.Resolver;
import csse374.AnalyzorBehaviors.AdaperRelationBehavior;
import csse374.AnalyzorBehaviors.AdapterClassBehavior;
import csse374.AnalyzorBehaviors.BadDecoratorClassUMLBehavior;
import csse374.AnalyzorBehaviors.BadDecoratorRelationsUMLBehavior;
import csse374.AnalyzorBehaviors.DecoratorClassBehavior;
import csse374.AnalyzorBehaviors.DecoratorRelationBehavior;
import csse374.AnalyzorBehaviors.DefaultRelationUMLBehavior;
import csse374.AnalyzorBehaviors.DefaultUMLBehavior;
import csse374.AnalyzorBehaviors.DepInvClassBehavior;
import csse374.AnalyzorBehaviors.DepInvRelationBehavior;
import csse374.AnalyzorBehaviors.FavorCompOverInheBehavior;
import csse374.AnalyzorBehaviors.FavorCompRelationBehavior;
import csse374.AnalyzorBehaviors.SingletonRelationUMLBehavior;
import csse374.AnalyzorBehaviors.SingletonUMLBehavior;
import csse374.AnalyzorBehaviors.UMLBehaviorTemplate;
import csse374.AnalyzorBehaviors.UMLRelationBehaviorTemplate;
import csse374.analyzers.AbstractAnalyzor;
import csse374.analyzers.AdaptorDetector;
import csse374.analyzers.AssociationAnalyzor;
import csse374.analyzers.BadDecoratorDetector;
import csse374.analyzers.DecoratorDetector;
import csse374.analyzers.DemoAnalyzor;
import csse374.analyzers.DemoInterfaceAnalyzor;
import csse374.analyzers.DemoSuperAnalyzor;
import csse374.analyzers.DependencyAnalyzor;
import csse374.analyzers.DependencyInversionDetector;
import csse374.analyzers.FavorCompositionDetector;
import csse374.analyzers.FindMethodAnalyzor;
import csse374.analyzers.InterfaceAnalyzor;
import csse374.analyzers.RecursiveAnalyzor;
import csse374.analyzers.RenderAnalyzor;
import csse374.analyzers.SequenceAnalyzor;
import csse374.analyzers.SingletonDetector;
import csse374.analyzers.SootAnalyzor;
import csse374.analyzers.SuperclassAnalyzor;
import csse374.analyzers.UMLAnalyzer;

public class Main {

	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
		Path filePath = Paths.get(System.getProperty("user.dir"), "build", "plantuml", "SampleUML.txt");
		Files.createDirectories(filePath.getParent());

		PreProcessor processor = new PreProcessor(args);
		UMLBehaviorTemplate classBehavior = new DefaultUMLBehavior();
		UMLBehaviorTemplate singletonUMLBehavior = new SingletonUMLBehavior();
		UMLBehaviorTemplate orangeBehavior = new FavorCompOverInheBehavior();
		UMLBehaviorTemplate adapterBehavior = new AdapterClassBehavior();
		UMLBehaviorTemplate depinvBehavior = new DepInvClassBehavior();
		
		UMLAnalyzer uml = new UMLAnalyzer(filePath.toFile());
		uml.addClassBehavior("default", classBehavior);
		uml.addClassBehavior("singleton", singletonUMLBehavior);
		uml.addClassBehavior("favorcomp", orangeBehavior);
		uml.addClassBehavior("adapter", adapterBehavior);
		uml.addClassBehavior("depinv", depinvBehavior);
		uml.addClassBehavior("decorator", new DecoratorClassBehavior());
		uml.addClassBehavior("baddecorator", new BadDecoratorClassUMLBehavior());
		uml.addRelationBehavior("singleton", new SingletonRelationUMLBehavior());
		uml.addRelationBehavior("favorcomp", new FavorCompRelationBehavior());
		uml.addRelationBehavior("default", new DefaultRelationUMLBehavior());
		uml.addRelationBehavior("adapter", new AdaperRelationBehavior());
		uml.addRelationBehavior("depinv", new DepInvRelationBehavior());
		uml.addRelationBehavior("decorator", new DecoratorRelationBehavior());
		uml.addRelationBehavior("baddecorator", new BadDecoratorRelationsUMLBehavior());

		AbstractAnalyzor render = new RenderAnalyzor(filePath.toFile());
		AbstractAnalyzor sootana = new SootAnalyzor();
		AbstractAnalyzor demo = new DemoAnalyzor();
		AbstractAnalyzor demointer = new DemoInterfaceAnalyzor();
		AbstractAnalyzor recur = new RecursiveAnalyzor();
		AbstractAnalyzor interfaces = new InterfaceAnalyzor();
		AbstractAnalyzor superclass = new SuperclassAnalyzor();
		AbstractAnalyzor association = new AssociationAnalyzor();
		AbstractAnalyzor dependency = new DependencyAnalyzor();
		AbstractAnalyzor methodfinder = new FindMethodAnalyzor();
		AbstractAnalyzor seqProcess = new SequenceAnalyzor(filePath.toFile());
		AbstractAnalyzor singletonDetector = new SingletonDetector();
		AbstractAnalyzor favorcomp = new FavorCompositionDetector();
		AbstractAnalyzor demosuper = new DemoSuperAnalyzor();
		AbstractAnalyzor adapterdetector = new AdaptorDetector();
		AbstractAnalyzor depinv = new DependencyInversionDetector();

		
		processor.addConfigs(new String[] { "-dir", "-demo", "-main", "-classes", "-f", "-sig", "-sigdir", "-depth",
				"-recur", "-superclass", "-association", "-dependency", "-interface", "-pattern", "-methodResolver", "-agg", "-methods","-classpath"});
		processor.addAnalyzor("uml", uml);
		processor.addAnalyzor("render", render);
		processor.addAnalyzor("-dir", sootana);
		processor.addAnalyzor("-association", association);
		processor.addAnalyzor("-dependency", dependency);
		processor.addAnalyzor("-demo", demo);
		processor.addAnalyzor("-superclass", superclass);
		processor.addAnalyzor("-interface", interfaces);
		processor.addAnalyzor("-recur", recur);
		processor.addAnalyzor("-sigdir", sootana);
		processor.addAnalyzor("-sig", methodfinder);
		processor.addAnalyzor("-sig", seqProcess);
		processor.addAnalyzor("favorcomp", favorcomp);
		processor.addAnalyzor("singleton", singletonDetector);
		processor.addAnalyzor("adapter", adapterdetector);
		processor.addAnalyzor("depinv", depinv);
		processor.addAnalyzor("decorator", new DecoratorDetector());
		processor.addAnalyzor("baddecorator", new BadDecoratorDetector());
		
		

//		try {
//			@SuppressWarnings("unchecked")
//			Class<? extends IUMLAnalyzerBehavior>  clazz = (Class<? extends IUMLAnalyzerBehavior>) Class.forName("csse374.revengd.detectors.BiDirectionBehavior");
//			IUMLAnalyzerBehavior redBehavior = clazz.newInstance();
//			uml.addBehavior(redBehavior);
//			
//			Class<? extends AbstractAnalyzor> bidirection = (Class<? extends AbstractAnalyzor>) Class.forName("csse374.revengd.detectors.BidirectionAnalyzor");
//			AbstractAnalyzor bidirectionana = bidirection.newInstance();
//			processor.addAnalyzor("bidirection", bidirectionana);
//			
//			Class<? extends Resolver> chain = (Class<? extends Resolver>) Class.forName("csse374.revengd.resolver.ChainResolveBehavior");
//			Resolver chainrev = chain.newInstance();
//			processor.addAggResolver("chain", chainrev);
//			
//			Class<? extends Resolver> intersection = (Class<? extends Resolver>) Class.forName("csse374.revengd.resolver.IntersectionResolveBehavior");
//			Resolver interrev = intersection.newInstance();
//			processor.addAggResolver("intersection", interrev);
//			
//			Class<? extends Resolver> union = (Class<? extends Resolver>) Class.forName("csse374.revengd.resolver.UnionResolveBehavior");
//			Resolver unionrev = union.newInstance();
//			processor.addAggResolver("union", unionrev);
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		
		try {
			processor.process();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}


		/*
		 * Milestone 3 commands
		 * 
		 * For singleton try: -dir
		 * /Users/taylorz1/Documents/School/CSSE374/Lab6-1/build/classes/main/
		 * -main problem.blueberrymuffinclient.FIXME -classes
		 * problem.blueberrymuffin.MuffinThreadExecutor,problem.blueberrymuffin.
		 * QueuePoller,problem.blueberrymuffin.RealThread,problem.
		 * blueberrymuffin.VirtualThread
		 */

		/*
		 * Milestone2 Command -dir
		 * /Users/KrystalYang/Documents/CSSE374/Lab4-1/build/classes/main/ -main
		 * headfirst.factory.pizzaaf.PizzaTestDrive -classes
		 * headfirst.factory.pizzaaf.PizzaTestDrive,headfirst.factory.pizzaaf.
		 * Veggies,headfirst.factory.pizzaaf.PizzaStore,headfirst.factory.
		 * pizzaaf.ClamPizza,headfirst.factory.pizzaaf.FreshClams,headfirst.
		 * factory.pizzaaf.Onion,headfirst.factory.pizzaaf.VeggiePizza,headfirst
		 * .factory.pizzaaf.Dough,headfirst.factory.pizzaaf.NYPizzaStore,
		 * headfirst.factory.pizzaaf.Pepperoni,headfirst.factory.pizzaaf.
		 * CheesePizza,headfirst.factory.pizzaaf.MarinaraSauce,headfirst.factory
		 * .pizzaaf.SlicedPepperoni,headfirst.factory.pizzaaf.MozzarellaCheese,
		 * headfirst.factory.pizzaaf.RedPepper,headfirst.factory.pizzaaf.Cheese,
		 * headfirst.factory.pizzaaf.FrozenClams,headfirst.factory.pizzaaf.
		 * Eggplant,headfirst.factory.pizzaaf.NYPizzaIngredientFactory,headfirst
		 * .factory.pizzaaf.PizzaIngredientFactory,headfirst.factory.pizzaaf.
		 * ChicagoPizzaStore,headfirst.factory.pizzaaf.
		 * ChicagoPizzaIngredientFactory,headfirst.factory.pizzaaf.
		 * PlumTomatoSauce,headfirst.factory.pizzaaf.PepperoniPizza,headfirst.
		 * factory.pizzaaf.Pizza,headfirst.factory.pizzaaf.Sauce,headfirst.
		 * factory.pizzaaf.Mushroom,headfirst.factory.pizzaaf.Garlic,headfirst.
		 * factory.pizzaaf.ThickCrustDough,headfirst.factory.pizzaaf.BlackOlives
		 * ,headfirst.factory.pizzaaf.ThinCrustDough,headfirst.factory.pizzaaf.
		 * Spinach,headfirst.factory.pizzaaf.ParmesanCheese,headfirst.factory.
		 * pizzaaf.Clams,headfirst.factory.pizzaaf.ReggianoCheese -dir
		 * /Users/KrystalYang/Documents/CSSE374/Lab2-1/build/classes/main/ -main
		 * problem.AppLauncherApppication -classes
		 * problem.DirectoryEvent,problem.ProcessRunner,problem.
		 * DirectoryMonitorService,problem.AppLauncher,problem.
		 * IDirectoryListener,problem.ApplicationLauncher,problem.DataFileRunner
		 * ,problem.ExecutableFileRunner,problem.DirectoryChangLogger,problem.
		 * AppLauncherApppication
		 * 
		 * -sigdir
		 * /Users/KrystalYang/Documents/CSSE374/Lab2-1/build/classes/main/ -main
		 * headfirst.designpatterns.observer.weather.WeatherStation -sig
		 * headfirst.designpatterns.observer.weather.WeatherStation,main,java.
		 * lang.String[]
		 * 
		 * -dir /Users/KrystalYang/Documents/CSSE374/RevEngD/build/classes/main/
		 * -main csse374.Project.Main -classes
		 * csse374.analyzers.UMLAnalyzer,csse374.analyzers.DependencyAnalyzor,
		 * csse374.analyzers.AssociationAnalyzor,csse374.analyzers.FileAnalyzor,
		 * csse374.analyzers.DemoAnalyzor,csse374.analyzers.InitAnalyzor,csse374
		 * .analyzers.SuperclassAnalyzor,csse374.analyzers.SootAnalyzor,csse374.
		 * analyzers.ClassAnalyzor,csse374.analyzers.SequenceAnalyzor,csse374.
		 * analyzers.RecursiveAnalyzor,csse374.analyzers.AbstractAnalyzor,
		 * csse374.analyzers.FindMethodAnalyzor,csse374.analyzers.
		 * InheritanceAnalyzor,csse374.analyzers.RenderAnalyzor,csse374.
		 * analyzers.DemoInterfaceAnalyzor,csse374.analyzers.InterfaceAnalyzor,
		 * csse374.analyzers.IAnalyzor,csse374.Project.Model,csse374.Project.
		 * IFilter,csse374.Project.PrivateFilter,csse374.Project.ProtectedFilter
		 * ,csse374.Project.Main,csse374.Project.PreProcessor,csse374.Project.
		 * PublicFilter,csse374.Project.RelationObject -sigdir
		 * /Users/KrystalYang/Documents/CSSE374/Lab4-1/build/classes/main/ -main
		 * headfirst.factory.pizzaaf.PizzaTestDrive -sig
		 * headfirst.factory.pizzaaf.PizzaStore,orderPizza,java.lang.String
		 * 
		 * 
		 */

		/*
		 * -dir
		 * /Users/taylorz1/Documents/School/CSSE374/RevEngD/build/classes/main/
		 * -main csse374.Project.Main -classes
		 * csse374.analyzers.UMLAnalyzer,csse374.analyzers.DependencyAnalyzor,
		 * csse374.analyzers.AssociationAnalyzor,csse374.analyzers.FileAnalyzor,
		 * csse374.analyzers.DemoAnalyzor,csse374.analyzers.InitAnalyzor,csse374
		 * .analyzers.SuperclassAnalyzor,csse374.analyzers.SootAnalyzor,csse374.
		 * analyzers.ClassAnalyzor,csse374.analyzers.SequenceAnalyzor,csse374.
		 * analyzers.RecursiveAnalyzor,csse374.analyzers.AbstractAnalyzor,
		 * csse374.analyzers.FindMethodAnalyzor,csse374.analyzers.
		 * InheritanceAnalyzor,csse374.analyzers.RenderAnalyzor,csse374.
		 * analyzers.DemoInterfaceAnalyzor,csse374.analyzers.InterfaceAnalyzor,
		 * csse374.analyzers.IAnalyzor,csse374.Project.Model,csse374.Project.
		 * IFilter,csse374.Project.PrivateFilter,csse374.Project.ProtectedFilter
		 * ,csse374.Project.Main,csse374.Project.PreProcessor,csse374.Project.
		 * PublicFilter,csse374.Project.RelationObject -dir
		 * /Users/taylorz1/Documents/School/CSSE374/Lab4-1/build/classes/main/
		 * -main headfirst.factory.pizzaaf.PizzaTestDrive -classes
		 * headfirst.factory.pizzaaf.Veggies,headfirst.factory.pizzaaf.
		 * PizzaStore,headfirst.factory.pizzaaf.ClamPizza,headfirst.factory.
		 * pizzaaf.FreshClams,headfirst.factory.pizzaaf.Onion,headfirst.factory.
		 * pizzaaf.VeggiePizza,headfirst.factory.pizzaaf.Dough,headfirst.factory
		 * .pizzaaf.NYPizzaStore,headfirst.factory.pizzaaf.Pepperoni,headfirst.
		 * factory.pizzaaf.CheesePizza,headfirst.factory.pizzaaf.MarinaraSauce,
		 * headfirst.factory.pizzaaf.SlicedPepperoni,headfirst.factory.pizzaaf.
		 * MozzarellaCheese,headfirst.factory.pizzaaf.RedPepper,headfirst.
		 * factory.pizzaaf.Cheese,headfirst.factory.pizzaaf.FrozenClams,
		 * headfirst.factory.pizzaaf.Eggplant,headfirst.factory.pizzaaf.
		 * NYPizzaIngredientFactory,headfirst.factory.pizzaaf.
		 * PizzaIngredientFactory,headfirst.factory.pizzaaf.ChicagoPizzaStore,
		 * headfirst.factory.pizzaaf.ChicagoPizzaIngredientFactory,headfirst.
		 * factory.pizzaaf.PlumTomatoSauce,headfirst.factory.pizzaaf.
		 * PepperoniPizza,headfirst.factory.pizzaaf.Pizza,headfirst.factory.
		 * pizzaaf.Sauce,headfirst.factory.pizzaaf.Mushroom,headfirst.factory.
		 * pizzaaf.Garlic,headfirst.factory.pizzaaf.ThickCrustDough,headfirst.
		 * factory.pizzaaf.BlackOlives,headfirst.factory.pizzaaf.ThinCrustDough,
		 * headfirst.factory.pizzaaf.Spinach,headfirst.factory.pizzaaf.
		 * ParmesanCheese,headfirst.factory.pizzaaf.Clams,headfirst.factory.
		 * pizzaaf.ReggianoCheese
		 * 
		 * -sigdir
		 * /Users/taylorz1/Documents/School/CSSE374/Lab2-1/build/classes/main/
		 * -main headfirst.designpatterns.observer.weather.WeatherStation -sig
		 * headfirst.designpatterns.observer.weather.WeatherStation,main,java.
		 * lang.String[]
		 */

		// -dir
		// /Users/KrystalYang/Desktop/Study/CSSE374/Homework/Lab1-1/build/classes/main/
		// -main problem.Main -classes
		// problem.Main,problem.AmazonParser,problem.DataStandardizer,problem.GoogleParser,problem.GrouponParser,problem.ILineParser,problem.MicrosoftParser,problem.StandardizerUI
		// -dir /Users/KrystalYang/Documents/CSSE374/RevEngD/build/classes/main/
		// -main csse374.Project.Main -classes
		// csse374.Project.AssociationAnalyzor,csse374.Project.Main,csse374.Project.FileAnalyzor,csse374.Project.ClassAnalyzor,csse374.Project.DemoAnalyzor,csse374.Project.DemoInterfaceAnalyzor,csse374.Project.AbstractAnalyzor,csse374.Project.FileAnalyzor,csse374.Project.IAnalyzor,csse374.Project.InheritanceAnalyzor,csse374.Project.IFilter,csse374.Project.InitAnalyzor,csse374.Project.InterfaceAnalyzor,csse374.Project.Model,csse374.Project.PreProcessor,csse374.Project.PrivateFilter,csse374.Project.PublicFilter,csse374.Project.ProtectedFilter,csse374.Project.RecursiveAnalyzor,csse374.Project.RelationObject,csse374.Project.SuperclassAnalyzorcsse374.Project.RenderAnalyzor,csse374.Project.UMLAnalyzercsse374.Project.SootAnalyzor
		// -sigdir
		// /Users/KrystalYang/Documents/CSSE374/RevEngD/build/classes/main/
		// -main csse374.Project.Main -sig
		// csse374.Project.Main,main,java.lang.String[]
		// -sigdir
		// /Users/taylorz1/Documents/School/CSSE374/Lab2-1/build/classes/main/
		// -main headfirst.designpatterns.observer.weather.WeatherStation -sig
		// headfirst.designpatterns.observer.weather.WeatherStation,main,java.lang.String[]
		// -sigdir
		// /Users/KrystalYang/Documents/CSSE374/Lab2-1/build/classes/main/ -main
		// headfirst.designpatterns.observer.weather.WeatherStation -sig
		// headfirst.designpatterns.observer.weather.WeatherStation,main,java.lang.String[]

		// /Users/taylorz1/Documents/School/CSSE374/RevEngD/build/classes/main/
		// csse374.Project.Main
		// -dir
		// /Users/KrystalYang/Desktop/Study/CSSE374/Homework/Lab1-1/build/classes/main/
		// -main problem.Main -classes
		// problem.Main,problem.AmazonParser,problem.DataStandardizer,problem.GoogleParser,problem.GrouponParser,problem.ILineParser,problem.MicrosoftPaser,problem.StandardizerUI
		// Demo C:
		// -demo javax.swing.JComponent -recur
		// -demo java.lang.String -interface

		// -dir /Users/KrystalYang/Documents/CSSE374/Lab6-1/build/classes/main/
		// -main problem.blueberrymuffinclient.FIXME -classes
		// problem.blueberrymuffinclient.FIXME,problem.blueberrymuffin.RealThread

	}

}
