package stringTransforms;

public class AggregateTransform implements TransformInterface {

	TransformInterface firstTransform;
	TransformInterface secondTransform;
	
	public AggregateTransform(TransformInterface t1, TransformInterface t2) {
		this.firstTransform = t1;
		this.secondTransform = t2;
	}
	@Override
	public String transform(String text) {
		String result1 = this.firstTransform.transform(text);
		String result2 = this.secondTransform.transform(result1);
		return result2;
	}

}
