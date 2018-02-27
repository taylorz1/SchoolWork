package problem;

/*
 * Copyright (c) 2008, 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Modified By: 
 * 		Chandan R. Rupakheti
 */

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.*;

/**
 * This class has been modified from the original WatchDir program found at:
 * https://docs.oracle.com/javase/tutorial/essential/io/examples/WatchDir.java.
 * 
 * This program listens to three kind of events in a supplied directory:
 * <ol>
 * <li>ENTRY_CREATE - When a file/folder gets created.</li>
 * <li>ENTRY_DELETE - When a file/folder gets deleted.</li>
 * <li>ENTRY_MODIFY - When a file/folder get modified.</li>
 * </ol>
 * 
 * Based on the event it launches the relevant application through the
 * {@link #handleDirectoryEvent(String, Path)} method.
 */
public class AppLauncher implements IDirectoryListener {
	/**
	 * This method is for internal use to delete the temporary file created by
	 * the {@link #stopGracefully()} method. As well as to kill all of the newly
	 * created process.
	 */
	
	Map<String, ProcessRunner> extensionToRunnerMap;
	
	public AppLauncher() {
		// DONE Auto-generated constructor stub
		this.extensionToRunnerMap = new HashMap<String, ProcessRunner>();
	}
	
	public void shutDown() {
		for (String k : this.extensionToRunnerMap.keySet()) {
			this.extensionToRunnerMap.get(k).processes.forEach(e -> e.destroy());
		}
	}

	@Override
	public void directoryChanged(DirectoryEvent e) {
		if(!e.getEventType().equals("ENTRY_CREATE"))
			return;
		String path = e.getFile().toString();
		String[] array = path.split("\\.");
		this.extensionToRunnerMap.get("."+array[array.length-1]).execute(e.getFile());
	}
	
	public void addRunner(String extension, ProcessRunner runner){
		this.extensionToRunnerMap.put(extension, runner);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		int count = 0;
		for(ProcessRunner e : this.extensionToRunnerMap.values()) {
			count += e.getProcesses().size();
		}
		return count;
	}
	
	
}