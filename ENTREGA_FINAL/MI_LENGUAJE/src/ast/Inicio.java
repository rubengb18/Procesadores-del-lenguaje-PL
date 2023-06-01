package ast;

import tablasimbolos.TablaSimbolos;

public class Inicio extends INI{
	private TDEF bloq1;
	private STRUCT bloq2;
	private D bloq3;
	private I bloq4;
	
	public Inicio(TDEF bloq1,STRUCT bloq2, D bloq3,I bloq4) {
		this.bloq1=bloq1;
		this.bloq2=bloq2;
		this.bloq3=bloq3;
		this.bloq4=bloq4;
	}
	public Inicio(STRUCT bloq2, D bloq3,I bloq4) {
		this.bloq2=bloq2;
		this.bloq3=bloq3;
		this.bloq4=bloq4;
	}
	public Inicio(D bloq3,I bloq4) {
		this.bloq3=bloq3;
		this.bloq4=bloq4;
	}
	public Inicio(I bloq4) {
		this.bloq4=bloq4;
	}
	
	public Inicio(TDEF bloq1,STRUCT bloq2,I bloq4) {
		this.bloq1=bloq1;
		this.bloq2=bloq2;
		this.bloq4=bloq4;
	}
	public Inicio(TDEF bloq1, D bloq3,I bloq4) {
		this.bloq1=bloq1;
		this.bloq3=bloq3;
		this.bloq4=bloq4;
	}
	public Inicio(STRUCT bloq2,I bloq4) {
		this.bloq2=bloq2;
		this.bloq4=bloq4;
	}
	public Inicio(TDEF bloq1,I bloq4) {
		this.bloq1=bloq1;
		this.bloq4=bloq4;
	}
	public TDEF bloq1() {return bloq1;} 
    public STRUCT bloq2() {return bloq2;} 
    public D bloq3() {return bloq3;} 
    public I bloq4() {return bloq4;} 
    
    
    public String toString() {
    	String c="{";
		if(bloq1==null) {
			if(bloq2==null) {
				if(bloq3==null) {
					c+="{"+bloq4().toString()+"}";
				}
				else {
					c+="{"+bloq3().toString()+"}"+" , "+"{"+bloq4().toString()+"}";
				}
			}
			else {
				c += "{"+bloq2().toString()+"}";
				if(bloq3==null) {
					c+="{"+bloq4().toString()+"}";
				}
				else {
					c+="{"+bloq3().toString()+"}"+" , "+"{"+bloq4().toString()+"}";
				}
			}
		}
		else {
			c += "{"+bloq1().toString()+"}"+" , ";
			if(bloq2==null) {
				if(bloq3==null) {
					c+="{"+bloq4().toString()+"}";
				}
				else {
					c+="{"+bloq3().toString()+"}"+" , "+"{"+bloq4().toString()+"}";
				}
			}
			else {
				c +="{"+ bloq2().toString()+"}"+" , ";
				if(bloq3==null) {
					c+="{"+bloq4().toString()+"}";
				}
				else {
					c+="{"+bloq3().toString()+"}"+" , "+"{"+bloq4().toString()+"}";
				}
			}

		}
    	
    	return c+"}";
    }
	@Override
	public KindIni kind() {
		return KindIni.INICIO;
	}
	@Override
	public void bind(TablaSimbolos tabla) {
		tabla.abreBloque();
		if(bloq1!=null) {
			bloq1.bind(tabla);
		}
		if(bloq2!=null) {
			bloq2.bind(tabla);
		}
		if(bloq3!=null) {
			bloq3.bind(tabla);
		}
		tabla.abreBloque();
		bloq4.bind(tabla);
		tabla.cierraBloque();
	}
	@Override
	public Tipo type() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String generateCode() {
		String codigo="(module\r\n" + 
				"(type $_sig_void (func))\r\n(type $_sig_i32i32i32 (func (param i32 i32 i32) ))\r\n(type $_sig_i32 (func (param i32)))\r\n(import \"runtime\" \"print\" (func $print (type $_sig_i32)))\r\n" + "(import \"runtime\" \"exceptionHandler\" (func $exception (type $_sig_i32)))\r\n"+
				"(import \"runtime\" \"read\" (func $read (result i32)))\r\n(memory 2000)\r\n"+"(global $SP (mut i32) (i32.const 0));; start of stack\r\n(global $MP (mut i32) (i32.const 0)) ;; mark pointer\r\n"+
				"(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4\r\n"
 + "(start $init)\r\n"
						+ "(func $reserveStack (param $size i32)\r\n" + 
						"   (result i32)\r\n" + 
						"   get_global $MP\r\n" + 
						"   get_global $SP\r\n" + 
						"   set_global $MP\r\n" + 
						"   get_global $SP\r\n" + 
						"   get_local $size\r\n" + 
						"   i32.add\r\n" + 
						"   set_global $SP\r\n" + 
						"   get_global $SP\r\n" + 
						"   get_global $NP\r\n" + 
						"   i32.gt_u\r\n" + 
						"   if\r\n" + 
						"   i32.const 3\r\ncall $exception\r\n"+
						"   end\r\n" + 
						")\r\n" + 
						"(func $freeStack (type $_sig_void)\r\n" + 
						"   get_global $MP\r\n" + 
						"   i32.load\r\n" + 
						"   i32.load offset=4\r\n" + 
						"   set_global $SP\r\n" + 
						"   get_global $MP\r\n" + 
						"   i32.load\r\n" + 
						"   set_global $MP   \r\n" + 
						")\r\n"+"(func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest\r\n" + 
								"   (param $src i32)\r\n" + 
								"   (param $dest i32)\r\n" + 
								"   (param $n i32)\r\n" + 
								"   block\r\n" + 
								"     loop\r\n" + 
								"       get_local $n\r\n" + 
								"       i32.eqz\r\n" + 
								"       br_if 1\r\n" + 
								"       get_local $n\r\n" + 
								"       i32.const 1\r\n" + 
								"       i32.sub\r\n" + 
								"       set_local $n\r\n" + 
								"       get_local $dest\r\n" + 
								"       get_local $src\r\n" + 
								"       i32.load\r\n" + 
								"       i32.store\r\n" + 
								"       get_local $dest\r\n" + 
								"       i32.const 4\r\n" + 
								"       i32.add\r\n" + 
								"       set_local $dest\r\n" + 
								"       get_local $src\r\n" + 
								"       i32.const 4\r\n" + 
								"       i32.add\r\n" + 
								"       set_local $src\r\n" + 
								"       br 0\r\n" + 
								"     end\r\n" + 
								"   end\r\n" + 
								")\r\n"+"(func $copyndir (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest\r\n" + 
										"   (param $src i32)\r\n" + 
										"   (param $dest i32)\r\n" + 
										"   (param $n i32)\r\n" + 
										"   block\r\n" + 
										"     loop\r\n" + 
										"       get_local $n\r\n" + 
										"       i32.eqz\r\n" + 
										"       br_if 1\r\n" + 
										"       get_local $n\r\n" + 
										"       i32.const 1\r\n" + 
										"       i32.sub\r\n" + 
										"       set_local $n\r\n" + 
										"       get_local $dest\r\n" + 
										"       get_local $src\r\n" + 
										"       i32.store\r\n" + 
										"       get_local $dest\r\n" + 
										"       i32.const 4\r\n" + 
										"       i32.add\r\n" + 
										"       set_local $dest\r\n" + 
										"       get_local $src\r\n" + 
										"       i32.const 4\r\n" + 
										"       i32.add\r\n" + 
										"       set_local $src\r\n" + 
										"       br 0\r\n" + 
										"     end\r\n" + 
										"   end\r\n" + 
										")\r\n";
		if(bloq1!=null) {
			codigo+=bloq1.generateCode();
		}
		if(bloq2!=null) {
			((Struct0) bloq2).calculaDeltas(0);
			codigo+=bloq2.generateCode();
		}
		if(bloq3!=null) {
			codigo+=bloq3.generateCode();
		}
		int localStart=bloq4.calculaDeltas(0);
		codigo+="(func $init\r\n   (local $localsStart i32)\r\n" + 
				"   (local $temp i32)\r\n" + 
				"   i32.const "+ (localStart+2)*4+"  ;; let this be the stack size needed (params+locals+2)*4\r\n" +  
				"   call $reserveStack  ;; returns old MP (dynamic link)\r\n" + 
				"   set_local $temp\r\n" + 
				"   get_global $MP\r\n" + 
				"   get_local $temp\r\n" + 
				"   i32.store\r\n" + 
				"   get_global $MP\r\n" + 
				"   get_global $SP\r\n" + 
				"   i32.store offset=4\r\n" + 
				"   get_global $MP\r\n" + 
				"   i32.const 8\r\n" + 
				"   i32.add\r\n" + 
				"   set_local $localsStart\r\n" + 
				"\r\n"+bloq4.generateCode()+ 
				"call $freeStack)\r\n"+")";
		return codigo;
	}
	
}
