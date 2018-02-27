package csse374.analyzers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import csse374.Project.Model;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.core.DiagramDescription;

public class RenderAnalyzor extends FileAnalyzor {
	public RenderAnalyzor(File file) {
		super(file);
	}
	private static final Logger logger = LogManager.getLogger(RenderAnalyzor.class.getName());

	
	@Override
	protected void fileAnalyze(Model model, File file) throws IOException {
		String umlSource = "";
		umlSource = new String(Files.readAllBytes(file.toPath()));
		SourceStringReader reader = new SourceStringReader(umlSource);
		try {
			Path filePath = Paths.get(System.getProperty("user.dir"), "build", "plantuml", "diagram.svg");
			Files.createDirectories(filePath.getParent());

			OutputStream outStream = new FileOutputStream(filePath.toFile());
			FileFormatOption option = new FileFormatOption(FileFormat.SVG, false);
			DiagramDescription description = reader.outputImage(outStream, option);
			logger.info("UML diagram generated at: " + filePath.toString());
			logger.info(description);
		} catch (Exception e) {
			logger.error("Cannot create file to store the UML diagram.", e);
		}
	}

}
