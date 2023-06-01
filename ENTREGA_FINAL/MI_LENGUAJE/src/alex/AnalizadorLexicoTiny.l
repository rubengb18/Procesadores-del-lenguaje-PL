package alex;

import errors.GestionErroresTiny;

%%
%cup
%line
%column
%class AnalizadorLexicoTiny
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = {digito}{digito}*
parteDecimal = {digito}* {digitoPositivo}
separador = [ \t\r\b\n]
comentario = \/\/[^\n]* 
barrabaja = _
identificador = {letra}({letra}|{digito}|{barrabaja})*
numeroEntero = [\+ \-]?{parteEntera}
numeroReal = [\+ \-]?{parteEntera}\.{parteDecimal}
operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = \/
parentesisApertura = \(
parentesisCierre = \)
corcheteApertura = \[
corcheteCierre = \]
operadorMayor = \>
operadorMenor = \<
igual = \=
operadorIgualComp = \==
operadorDist = \!=
if = if
while = while
for=for
puntocoma = \;
llaveApertura = \{
llaveCierre = \}
param=\&
else=else
void=void
print=print
read=read
def=def
new=new
typedef=typedef
return=return
bool = bool
true = true
false = false
int = int
struct=struct
array = array
operadorAnd = and
operadorOr = or
coma  = ,
punto = .
%%
{separador}               {}
{comentario}              {}
{void}					  {return ops.unidadVoid();}
{print}					  {return ops.unidadPrint();}
{typedef}				  {return ops.unidadTypedef();}
{read}					  {return ops.unidadRead();}
{struct}				  {return ops.unidadStruct();}
{return}			      {return ops.unidadReturn();}
{def}					  {return ops.unidadDef();}
{if}					  {return ops.unidadIf();}
{new}					  {return ops.unidadNew();}
{else}					  {return ops.unidadElse();}
{operadorAnd}             {return ops.unidadOperadorAnd();}
{operadorOr}              {return ops.unidadOperadorOr();}
{while}					  {return ops.unidadWhile();}
{for}					  {return ops.unidadFor();}
{bool}					  {return ops.unidadBool();}
{true}					  {return ops.unidadTrue();}
{false}					  {return ops.unidadFalse();}
{int}					  {return ops.unidadInt();}
{array}					  {return ops.unidadArray();}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{corcheteApertura}        {return ops.unidadCorcheteApertura();}
{corcheteCierre}          {return ops.unidadCorcheteCierre();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{llaveApertura}           {return ops.unidadLlaveAp();}
{llaveCierre}             {return ops.unidadLlaveCierre();} 
{operadorMayor}           {return ops.unidadOperadorMayor();}
{param}                   {return ops.unidadParam();}
{operadorMenor}           {return ops.unidadOperadorMenor();}
{operadorIgualComp}       {return ops.unidadOperadorIgualComp();}
{operadorDist}            {return ops.unidadOperadorDist();}
{igual}                   {return ops.unidadIgual();} 
{coma}                    {return ops.unidadComa();}
{puntocoma}               {return ops.unidadPuntoComa();}
{punto}               {return ops.unidadPunto();}
[^]                       {errores.errorLexico(fila(),columna(),lexema());}  
