package csse374.analyzers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import csse374.Project.Model;

public abstract class FileAnalyzor extends AbstractAnalyzor {
	private File file;

	public FileAnalyzor(File file){
		this.file = file;
	}
	
	@Override
	public void analyze(Model model){
		try {
			fileAnalyze(model,file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	abstract protected void fileAnalyze(Model model, File file) throws IOException;

}
