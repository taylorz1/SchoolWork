package treedb;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TreePath implements Iterable<String> {
	String rawPath;
	String path;
	List<String> segments;
	
	public static TreePath at(String... pathOrSegments) {
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < pathOrSegments.length; ++i) {
			builder.append(normalize(pathOrSegments[i]));
			
			if(i != pathOrSegments.length - 1)
				builder.append("/");
		}
		
		return new TreePath(builder.toString());
	}
	
	public static TreePath from(INode node) {
		StringBuilder builder = new StringBuilder();
		
		while(node != null && node.getKey() != null) {
			String key = node.getKey();
			builder.insert(0, "/");
			builder.insert(0, key);
		}

		return new TreePath(builder.toString());
	}

	private static String normalize(String path) {
		if(path.startsWith("/"))
			path = path.substring(1);

		if(path.endsWith("/"))
			path = path.substring(0, path.length() - 1);
		
		String[] pathSegments = path.split("/");
		for(String segment: pathSegments) {
			if(segment.trim().length() == 0)
				throw new IllegalArgumentException("The path has an empty segment");
		}
		
		return path;
	}
	

	private TreePath(String path) {
		this.path = normalize(path);
		this.segments = Arrays.asList(this.path.split("/"));
	}
	
	public String getPath() {
		return this.path;
	}
	
	public String getPartialSegments(int startIndex, int endIndex) {
		if(endIndex < 0) {
			endIndex = this.segments.size() + endIndex + 1;
		}
		
		StringBuilder buffer = new StringBuilder();
		for(int i = startIndex; i < endIndex; ++i) {
			buffer.append(this.getSegmentAt(i));
			
			if(i != endIndex -1)
				buffer.append("/");
		}
		return buffer.toString();
	}
	
	public String getFirstSegment() {
		return this.segments.get(0);
	}
	
	public String getLastSegment() {
		return this.segments.get(this.segments.size() - 1);
	}
	
	public String getSegmentAt(int index) {
		if(index < 0)
			index = this.segments.size() + index;
		return this.segments.get(index);
	}
	
	public int getSegmentsLength() {
		return this.segments.size();
	}
	
	public List<String> getSegments() {
		return Collections.unmodifiableList(this.segments);
	}

	@Override
	public Iterator<String> iterator() {
		return Collections.unmodifiableList(this.segments).iterator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + path.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreePath other = (TreePath) obj;
		return path.equals(other.path);
	}

	@Override
	public String toString() {
		return this.getPath();
	}
}
