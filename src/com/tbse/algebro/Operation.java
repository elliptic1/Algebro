package com.tbse.algebro;

import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public class Operation extends Symbol {
	
	
	public Expression LHS;
	public Expression RHS;
	public int operationCode;
	public int result;
	
	public Operation(float pX, float pY, TiledTextureRegion pTiledTextureRegion,
			MainGameActivity main, Expression LHS, Expression RHS, int operationCode, boolean isRemovable) {
		// operationCode is 0 plus, 1 minus, 2 mult, 3 div
		super(pX, pY, pTiledTextureRegion, main, operationCode, isRemovable);
		this.LHS = LHS;
		this.RHS = RHS;
		this.operationCode = operationCode;

		if (LHS.isConstant && RHS.isConstant) {
			switch(operationCode) {
			case 11:
				this.result = LHS.value + RHS.value;
				break;
			case 12:
				this.result = LHS.value - RHS.value;
				break;
			case 13:
				this.result = LHS.value * RHS.value;
				break;
			}
		}
		
	}

}