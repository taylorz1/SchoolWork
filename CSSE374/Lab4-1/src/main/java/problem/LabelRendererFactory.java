package problem;

public class LabelRendererFactory implements IRendererFactory{

	private Label labeltoRender;
	
	public LabelRendererFactory(Label labeltoRender) {
		this.labeltoRender = labeltoRender;
	}
	
	@Override
	public IWindowsRenderer createWindowsRenderer() {
		// TODO Auto-generated method stub
		return new WindowsLabelRenderer(this.labeltoRender);
	}

	@Override
	public ILinuxRenderer createLinuxRenderer() {
		// TODO Auto-generated method stub
		return new LinuxLabelRenderer(this.labeltoRender);
	}

	@Override
	public IMacOSRenderer createMacOSRenderer() {
		// TODO Auto-generated method stub
		return new MacOSLabelRenderer(this.labeltoRender);
	}
	
	

}
