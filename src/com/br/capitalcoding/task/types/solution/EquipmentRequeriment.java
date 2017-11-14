package com.br.capitalcoding.task.types.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.br.capitalcoding.task.types.Solution;
import com.br.capitalcoding.task.types.SolutionTask;

public class EquipmentRequeriment extends Solution{

	@Override
	public boolean meetCondition() {
		return context.getInventory().contains(1351) || context.getEquipment().contains(1351);
	}

	@Override
	public List<ArrayList<SolutionSet>> taskOptions() {
		SolutionSet set = new SolutionSet(); //set
		ArrayList<SolutionSet> sets = new ArrayList<SolutionSet>() {{
			add(set);
		}};
		return Arrays.asList(sets);
	}

	

}
