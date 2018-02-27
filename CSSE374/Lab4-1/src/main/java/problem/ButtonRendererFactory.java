package problem;

public class ButtonRendererFactory implements IRendererFactory{
	private Button labeltoRender;
	
	public ButtonRendererFactory(Button labeltoRender) {
		this.labeltoRender = labeltoRender;
	}
	
	@Override
	public IWindowsRenderer createWindowsRenderer() {
		// TODO Auto-generated method stub
		return new WindowsButtonRenderer(this.labeltoRender);
	}

	@Override
	public ILinuxRenderer createLinuxRenderer() {
		// TODO Auto-generated method stub
		return new LinuxButtonRenderer(this.labeltoRender);
	}

	@Override
	public IMacOSRenderer createMacOSRenderer() {
		// TODO Auto-generated method stub
		return new MacOSButtonRenderer(this.labeltoRender);
	}
}
