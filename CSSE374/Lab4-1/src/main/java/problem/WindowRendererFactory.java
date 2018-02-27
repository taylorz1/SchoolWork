package problem;

public class WindowRendererFactory implements IRendererFactory {
	private Window ToRender;
	
	public WindowRendererFactory(Window ToRender) {
		this.ToRender = ToRender;
	}
	
	@Override
	public IWindowsRenderer createWindowsRenderer() {
		// TODO Auto-generated method stub
		return new WindowsWindowRenderer(this.ToRender);
	}

	@Override
	public ILinuxRenderer createLinuxRenderer() {
		// TODO Auto-generated method stub
		return new LinuxWindowRenderer(this.ToRender);
	}

	@Override
	public IMacOSRenderer createMacOSRenderer() {
		// TODO Auto-generated method stub
		return new MacOSWindowRenderer(this.ToRender);
	}
}
