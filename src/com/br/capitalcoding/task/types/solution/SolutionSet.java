package com.br.capitalcoding.task.types.solution;

import java.util.ArrayList;
import java.util.Arrays;

import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.task.types.PreConditionTask;

public class SolutionSet extends ArrayList<PreConditionTask>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//List<SolutionTask> set;
	public SolutionSet(PreConditionTask... task) {		
		super.addAll(Arrays.asList(task));
	}
	
}
