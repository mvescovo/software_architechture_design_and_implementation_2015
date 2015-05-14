package model;

import java.io.Serializable;

import model.interfaces.DicePair;

public class DicePairImpl implements DicePair, Serializable {
	private static final long serialVersionUID = 1696519781925901108L;
	private int dice1;
	private int dice2;
	private int numFaces;
	
	public DicePairImpl(int dice1, int dice2, int numFaces) {
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.numFaces = numFaces;
	}

	@Override
	public int getDice1() {
		return this.dice1;
	}

	@Override
	public int getDice2() {
		return this.dice2;
	}

	@Override
	public int getNumFaces() {
		return this.numFaces;
	}

}
