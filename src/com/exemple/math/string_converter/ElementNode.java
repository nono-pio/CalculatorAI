package com.exemple.math.string_converter;

import java.util.ArrayList;

import com.exemple.math.ParentClass.Element;

public class ElementNode {

	ArrayList<ElementNode> child = new ArrayList<>();
	PriorityOperator op;
	int indexRoot;
	
	public ElementNode(PriorityOperator op)
	{
		this.op = op;
	}
	public ElementNode(int index)
	{
		this.op = PriorityOperator.RootElem;
		indexRoot = index;
	}
	
	public ElementNode addChild(ElementNode node)
	{
		if (op == PriorityOperator.Root) return node;
		
		if (node.op.power > op.power)
			return node.addChild(this);
		else if (node.op.power  == op.power && (op == PriorityOperator.Add || op == PriorityOperator.Mult) )
		{
			addAllChilds(node.child);
			
		} else
		{
			int lastIndex = child.size() - 1;
			if (lastIndex >= 0 && child.get(lastIndex).op.power == node.op.power && node.op != PriorityOperator.RootElem)
				child.get(lastIndex).addChild(node);
			else
				child.add(node);
		}
		return this;
	}
	private void addAllChilds(ArrayList<ElementNode> nodes)
	{
		child.addAll(nodes);
	}
	
	public Element toElement(ArrayList<Element> rootElement)
	{
		if (op == PriorityOperator.RootElem) return rootElement.get(indexRoot);
		
		Element[] childsElement = new Element[child.size()];
		for (int i = 0; i < childsElement.length; i++)
			childsElement[i] = child.get(i).toElement(rootElement);
		
		return op.toElement(childsElement);
	}

	@Override
	public String toString() {
		return op + " : " + child;
	}
}
