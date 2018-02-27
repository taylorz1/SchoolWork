package render;

import java.util.HashMap;
import java.util.Map;

import tiles.ITile;

public class ASCIIRenderer implements IRenderer {
	
	String toRender;
	
	public ASCIIRenderer(String toRender) {
		this.toRender = toRender;
	}
	
	@Override
	public void render() {
		System.out.print(toRender);
	}

}
