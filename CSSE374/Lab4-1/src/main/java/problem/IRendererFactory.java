package problem;

public interface IRendererFactory {
	
public IWindowsRenderer createWindowsRenderer();
public ILinuxRenderer createLinuxRenderer();
public IMacOSRenderer createMacOSRenderer();
}
