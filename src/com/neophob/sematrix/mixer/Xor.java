package com.neophob.sematrix.mixer;

import com.neophob.sematrix.generator.Generator;
import com.neophob.sematrix.glue.Visual;

public class Xor extends Mixer {

	public Xor() {
		super(MixerName.XOR);
	}

	public int[] getBuffer(Visual visual) {
		short cr_s,cg_s,cb_s;
		short cr_d,cg_d,cb_d;
		int col;
		
		if (visual.getEffect2() == null) {
			return visual.getEffect1Buffer();
		}
		
		Generator gen1 = visual.getGenerator1();		
		int[] src1 = visual.getEffect1Buffer();
		int[] src2 = visual.getEffect2Buffer();
		int[] dst = new int [gen1.internalBuffer.length];

		for (int i=0; i<gen1.internalBuffer.length; i++){
			col = src1[i];
    		cr_s=(short) ((col>>16)&255);
    		cg_s=(short) ((col>>8) &255);
    		cb_s=(short) ( col     &255);
    		
			col = src2[i];
    		cr_d=(short) ((col>>16)&255);
    		cg_d=(short) ((col>>8) &255);
    		cb_d=(short) ( col     &255);
    		
    		cr_d = (short) (cr_d ^ cr_s);
    		cg_d = (short) (cg_d ^ cg_s);
    		cb_d = (short) (cb_d ^ cb_s);
    		
    		dst[i]=(int) ((cr_d << 16) | (cg_d << 8) | cb_d);
          }
	
		return dst;
	}

}