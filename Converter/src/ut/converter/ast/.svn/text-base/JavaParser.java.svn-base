// $ANTLR 3.4 E:\\workspace_16_05_10\\Converter\\lib\\Java.g 2012-05-10 15:01:12

	package ut.converter.ast;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


/** A Java 1.5 grammar for ANTLR v3 derived from the spec
 *
 *  This is a very close representation of the spec; the changes
 *  are comestic (remove left recursion) and also fixes (the spec
 *  isn't exactly perfect).  I have run this on the 1.4.2 source
 *  and some nasty looking enums from 1.5, but have not really
 *  tested for 1.5 compatibility.
 *
 *  I built this with: java -Xmx100M org.antlr.Tool java.g 
 *  and got two errors that are ok (for now):
 *  java.g:691:9: Decision can match input such as
 *    "'0'..'9'{'E', 'e'}{'+', '-'}'0'..'9'{'D', 'F', 'd', 'f'}"
 *    using multiple alternatives: 3, 4
 *  As a result, alternative(s) 4 were disabled for that input
 *  java.g:734:35: Decision can match input such as "{'$', 'A'..'Z',
 *    '_', 'a'..'z', '\u00C0'..'\u00D6', '\u00D8'..'\u00F6',
 *    '\u00F8'..'\u1FFF', '\u3040'..'\u318F', '\u3300'..'\u337F',
 *    '\u3400'..'\u3D2D', '\u4E00'..'\u9FFF', '\uF900'..'\uFAFF'}"
 *    using multiple alternatives: 1, 2
 *  As a result, alternative(s) 2 were disabled for that input
 *
 *  You can turn enum on/off as a keyword :)
 *
 *  Version 1.0 -- initial release July 5, 2006 (requires 3.0b2 or higher)
 *
 *  Primary author: Terence Parr, July 2006
 *
 *  Version 1.0.1 -- corrections by Koen Vanderkimpen & Marko van Dooren,
 *      October 25, 2006;
 *      fixed normalInterfaceDeclaration: now uses typeParameters instead
 *          of typeParameter (according to JLS, 3rd edition)
 *      fixed castExpression: no longer allows expression next to type
 *          (according to semantics in JLS, in contrast with syntax in JLS)
 *
 *  Version 1.0.2 -- Terence Parr, Nov 27, 2006
 *      java spec I built this from had some bizarre for-loop control.
 *          Looked weird and so I looked elsewhere...Yep, it's messed up.
 *          simplified.
 *
 *  Version 1.0.3 -- Chris Hogue, Feb 26, 2007
 *      Factored out an annotationName rule and used it in the annotation rule.
 *          Not sure why, but typeName wasn't recognizing references to inner
 *          annotations (e.g. @InterfaceName.InnerAnnotation())
 *      Factored out the elementValue section of an annotation reference.  Created 
 *          elementValuePair and elementValuePairs rules, then used them in the 
 *          annotation rule.  Allows it to recognize annotation references with 
 *          multiple, comma separated attributes.
 *      Updated elementValueArrayInitializer so that it allows multiple elements.
 *          (It was only allowing 0 or 1 element).
 *      Updated localVariableDeclaration to allow annotations.  Interestingly the JLS
 *          doesn't appear to indicate this is legal, but it does work as of at least
 *          JDK 1.5.0_06.
 *      Moved the Identifier portion of annotationTypeElementRest to annotationMethodRest.
 *          Because annotationConstantRest already references variableDeclarator which 
 *          has the Identifier portion in it, the parser would fail on constants in 
 *          annotation definitions because it expected two identifiers.  
 *      Added optional trailing ';' to the alternatives in annotationTypeElementRest.
 *          Wouldn't handle an inner interface that has a trailing ';'.
 *      Swapped the expression and type rule reference order in castExpression to 
 *          make it check for genericized casts first.  It was failing to recognize a
 *          statement like  "Class<Byte> TYPE = (Class<Byte>)...;" because it was seeing
 *          'Class<Byte' in the cast expression as a less than expression, then failing 
 *          on the '>'.
 *      Changed createdName to use typeArguments instead of nonWildcardTypeArguments.
 *          Again, JLS doesn't seem to allow this, but java.lang.Class has an example of
 *          of this construct.
 *      Changed the 'this' alternative in primary to allow 'identifierSuffix' rather than
 *          just 'arguments'.  The case it couldn't handle was a call to an explicit
 *          generic method invocation (e.g. this.<E>doSomething()).  Using identifierSuffix
 *          may be overly aggressive--perhaps should create a more constrained thisSuffix rule?
 *      
 *  Version 1.0.4 -- Hiroaki Nakamura, May 3, 2007
 *
 *  Fixed formalParameterDecls, localVariableDeclaration, forInit,
 *  and forVarControl to use variableModifier* not 'final'? (annotation)?
 *
 *  Version 1.0.5 -- Terence, June 21, 2007
 *  --a[i].foo didn't work. Fixed unaryExpression
 *
 *  Version 1.0.6 -- John Ridgway, March 17, 2008
 *      Made "assert" a switchable keyword like "enum".
 *      Fixed compilationUnit to disallow "annotation importDeclaration ...".
 *      Changed "Identifier ('.' Identifier)*" to "qualifiedName" in more 
 *          places.
 *      Changed modifier* and/or variableModifier* to classOrInterfaceModifiers,
 *          modifiers or variableModifiers, as appropriate.
 *      Renamed "bound" to "typeBound" to better match language in the JLS.
 *      Added "memberDeclaration" which rewrites to methodDeclaration or 
 *      fieldDeclaration and pulled type into memberDeclaration.  So we parse 
 *          type and then move on to decide whether we're dealing with a field
 *          or a method.
 *      Modified "constructorDeclaration" to use "constructorBody" instead of
 *          "methodBody".  constructorBody starts with explicitConstructorInvocation,
 *          then goes on to blockStatement*.  Pulling explicitConstructorInvocation
 *          out of expressions allowed me to simplify "primary".
 *      Changed variableDeclarator to simplify it.
 *      Changed type to use classOrInterfaceType, thus simplifying it; of course
 *          I then had to add classOrInterfaceType, but it is used in several 
 *          places.
 *      Fixed annotations, old version allowed "@X(y,z)", which is illegal.
 *      Added optional comma to end of "elementValueArrayInitializer"; as per JLS.
 *      Changed annotationTypeElementRest to use normalClassDeclaration and 
 *          normalInterfaceDeclaration rather than classDeclaration and 
 *          interfaceDeclaration, thus getting rid of a couple of grammar ambiguities.
 *      Split localVariableDeclaration into localVariableDeclarationStatement
 *          (includes the terminating semi-colon) and localVariableDeclaration.  
 *          This allowed me to use localVariableDeclaration in "forInit" clauses,
 *           simplifying them.
 *      Changed switchBlockStatementGroup to use multiple labels.  This adds an
 *          ambiguity, but if one uses appropriately greedy parsing it yields the
 *           parse that is closest to the meaning of the switch statement.
 *      Renamed "forVarControl" to "enhancedForControl" -- JLS language.
 *      Added semantic predicates to test for shift operations rather than other
 *          things.  Thus, for instance, the string "< <" will never be treated
 *          as a left-shift operator.
 *      In "creator" we rule out "nonWildcardTypeArguments" on arrayCreation, 
 *          which are illegal.
 *      Moved "nonWildcardTypeArguments into innerCreator.
 *      Removed 'super' superSuffix from explicitGenericInvocation, since that
 *          is only used in explicitConstructorInvocation at the beginning of a
 *           constructorBody.  (This is part of the simplification of expressions
 *           mentioned earlier.)
 *      Simplified primary (got rid of those things that are only used in
 *          explicitConstructorInvocation).
 *      Lexer -- removed "Exponent?" from FloatingPointLiteral choice 4, since it
 *          led to an ambiguity.
 *
 *      This grammar successfully parses every .java file in the JDK 1.5 source 
 *          tree (excluding those whose file names include '-', which are not
 *          valid Java compilation units).
 *
 *  Known remaining problems:
 *      "Letter" and "JavaIDDigit" are wrong.  The actual specification of
 *      "Letter" should be "a character for which the method
 *      Character.isJavaIdentifierStart(int) returns true."  A "Java 
 *      letter-or-digit is a character for which the method 
 *      Character.isJavaIdentifierPart(int) returns true."
 */
@SuppressWarnings({"all", "warnings", "unchecked"})
public class JavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ANNOTATION", "ARGS", "ASSERT", "BLOCK", "CLASS", "CLASS_BODY", "CLASS_DECLARATION", "COMMENT", "CONSTRUCTOR", "CharacterLiteral", "DOT", "DecimalLiteral", "ENUM", "EQ", "EXTENDS", "EscapeSequence", "Exponent", "FloatTypeSuffix", "FloatingPointLiteral", "GT", "HexDigit", "HexLiteral", "IMPLEMENTS", "IMPORT", "Identifier", "IntegerTypeSuffix", "JavaIDDigit", "LINE_COMMENT", "LS", "Letter", "MEMBER", "METHOD", "MODIFIER_LIST", "OctalEscape", "OctalLiteral", "PACKAGE", "PARAMETER", "PARAMETERS", "PRIMARY", "SEMI", "STAR", "STATIC", "SUPER", "StringLiteral", "TEMPLATE", "TYPE", "TYPE_LIST", "TYPE_PARAMETERS", "UnicodeEscape", "VOID", "WS", "'!'", "'!='", "'%'", "'%='", "'&&'", "'&'", "'&='", "'('", "')'", "'*='", "'+'", "'++'", "'+='", "','", "'-'", "'--'", "'-='", "'...'", "'/'", "'/='", "':'", "'=='", "'?'", "'@'", "'['", "']'", "'^'", "'^='", "'abstract'", "'boolean'", "'break'", "'byte'", "'case'", "'catch'", "'char'", "'continue'", "'default'", "'do'", "'double'", "'else'", "'false'", "'final'", "'finally'", "'float'", "'for'", "'if'", "'instanceof'", "'int'", "'interface'", "'long'", "'native'", "'new'", "'null'", "'private'", "'protected'", "'public'", "'return'", "'short'", "'strictfp'", "'switch'", "'synchronized'", "'this'", "'throw'", "'throws'", "'transient'", "'true'", "'try'", "'volatile'", "'while'", "'{'", "'|'", "'|='", "'||'", "'}'", "'~'"
    };

    public static final int EOF=-1;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__90=90;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__99=99;
    public static final int T__100=100;
    public static final int T__101=101;
    public static final int T__102=102;
    public static final int T__103=103;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int T__107=107;
    public static final int T__108=108;
    public static final int T__109=109;
    public static final int T__110=110;
    public static final int T__111=111;
    public static final int T__112=112;
    public static final int T__113=113;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int T__116=116;
    public static final int T__117=117;
    public static final int T__118=118;
    public static final int T__119=119;
    public static final int T__120=120;
    public static final int T__121=121;
    public static final int T__122=122;
    public static final int T__123=123;
    public static final int T__124=124;
    public static final int T__125=125;
    public static final int T__126=126;
    public static final int T__127=127;
    public static final int T__128=128;
    public static final int T__129=129;
    public static final int ANNOTATION=4;
    public static final int ARGS=5;
    public static final int ASSERT=6;
    public static final int BLOCK=7;
    public static final int CLASS=8;
    public static final int CLASS_BODY=9;
    public static final int CLASS_DECLARATION=10;
    public static final int COMMENT=11;
    public static final int CONSTRUCTOR=12;
    public static final int CharacterLiteral=13;
    public static final int DOT=14;
    public static final int DecimalLiteral=15;
    public static final int ENUM=16;
    public static final int EQ=17;
    public static final int EXTENDS=18;
    public static final int EscapeSequence=19;
    public static final int Exponent=20;
    public static final int FloatTypeSuffix=21;
    public static final int FloatingPointLiteral=22;
    public static final int GT=23;
    public static final int HexDigit=24;
    public static final int HexLiteral=25;
    public static final int IMPLEMENTS=26;
    public static final int IMPORT=27;
    public static final int Identifier=28;
    public static final int IntegerTypeSuffix=29;
    public static final int JavaIDDigit=30;
    public static final int LINE_COMMENT=31;
    public static final int LS=32;
    public static final int Letter=33;
    public static final int MEMBER=34;
    public static final int METHOD=35;
    public static final int MODIFIER_LIST=36;
    public static final int OctalEscape=37;
    public static final int OctalLiteral=38;
    public static final int PACKAGE=39;
    public static final int PARAMETER=40;
    public static final int PARAMETERS=41;
    public static final int PRIMARY=42;
    public static final int SEMI=43;
    public static final int STAR=44;
    public static final int STATIC=45;
    public static final int SUPER=46;
    public static final int StringLiteral=47;
    public static final int TEMPLATE=48;
    public static final int TYPE=49;
    public static final int TYPE_LIST=50;
    public static final int TYPE_PARAMETERS=51;
    public static final int UnicodeEscape=52;
    public static final int VOID=53;
    public static final int WS=54;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public JavaParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public JavaParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[407+1];
         

    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return JavaParser.tokenNames; }
    public String getGrammarFileName() { return "E:\\workspace_16_05_10\\Converter\\lib\\Java.g"; }


    public static class compilationUnit_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "compilationUnit"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:231:1: compilationUnit : ( annotations ( packageDeclaration ( importDeclaration )* ( typeDeclaration )* | classOrInterfaceDeclaration ( typeDeclaration )* ) | ( packageDeclaration )? ( importDeclaration )* ( typeDeclaration )* );
    public final JavaParser.compilationUnit_return compilationUnit() throws RecognitionException {
        JavaParser.compilationUnit_return retval = new JavaParser.compilationUnit_return();
        retval.start = input.LT(1);

        int compilationUnit_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.annotations_return annotations1 =null;

        JavaParser.packageDeclaration_return packageDeclaration2 =null;

        JavaParser.importDeclaration_return importDeclaration3 =null;

        JavaParser.typeDeclaration_return typeDeclaration4 =null;

        JavaParser.classOrInterfaceDeclaration_return classOrInterfaceDeclaration5 =null;

        JavaParser.typeDeclaration_return typeDeclaration6 =null;

        JavaParser.packageDeclaration_return packageDeclaration7 =null;

        JavaParser.importDeclaration_return importDeclaration8 =null;

        JavaParser.typeDeclaration_return typeDeclaration9 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:232:5: ( annotations ( packageDeclaration ( importDeclaration )* ( typeDeclaration )* | classOrInterfaceDeclaration ( typeDeclaration )* ) | ( packageDeclaration )? ( importDeclaration )* ( typeDeclaration )* )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==78) ) {
                int LA8_1 = input.LA(2);

                if ( (synpred5_Java()) ) {
                    alt8=1;
                }
                else if ( (true) ) {
                    alt8=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA8_0==EOF||LA8_0==CLASS||LA8_0==ENUM||LA8_0==IMPORT||LA8_0==PACKAGE||LA8_0==SEMI||LA8_0==STATIC||LA8_0==83||LA8_0==96||LA8_0==103||(LA8_0 >= 108 && LA8_0 <= 110)||LA8_0==113) ) {
                alt8=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }
            switch (alt8) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:232:9: annotations ( packageDeclaration ( importDeclaration )* ( typeDeclaration )* | classOrInterfaceDeclaration ( typeDeclaration )* )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_annotations_in_compilationUnit282);
                    annotations1=annotations();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotations1.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:9: ( packageDeclaration ( importDeclaration )* ( typeDeclaration )* | classOrInterfaceDeclaration ( typeDeclaration )* )
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==PACKAGE) ) {
                        alt4=1;
                    }
                    else if ( (LA4_0==CLASS||LA4_0==ENUM||LA4_0==STATIC||LA4_0==78||LA4_0==83||LA4_0==96||LA4_0==103||(LA4_0 >= 108 && LA4_0 <= 110)||LA4_0==113) ) {
                        alt4=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 0, input);

                        throw nvae;

                    }
                    switch (alt4) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:13: packageDeclaration ( importDeclaration )* ( typeDeclaration )*
                            {
                            pushFollow(FOLLOW_packageDeclaration_in_compilationUnit296);
                            packageDeclaration2=packageDeclaration();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, packageDeclaration2.getTree());

                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:32: ( importDeclaration )*
                            loop1:
                            do {
                                int alt1=2;
                                int LA1_0 = input.LA(1);

                                if ( (LA1_0==IMPORT) ) {
                                    alt1=1;
                                }


                                switch (alt1) {
                            	case 1 :
                            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:32: importDeclaration
                            	    {
                            	    pushFollow(FOLLOW_importDeclaration_in_compilationUnit298);
                            	    importDeclaration3=importDeclaration();

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, importDeclaration3.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop1;
                                }
                            } while (true);


                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:51: ( typeDeclaration )*
                            loop2:
                            do {
                                int alt2=2;
                                int LA2_0 = input.LA(1);

                                if ( (LA2_0==CLASS||LA2_0==ENUM||LA2_0==SEMI||LA2_0==STATIC||LA2_0==78||LA2_0==83||LA2_0==96||LA2_0==103||(LA2_0 >= 108 && LA2_0 <= 110)||LA2_0==113) ) {
                                    alt2=1;
                                }


                                switch (alt2) {
                            	case 1 :
                            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:51: typeDeclaration
                            	    {
                            	    pushFollow(FOLLOW_typeDeclaration_in_compilationUnit301);
                            	    typeDeclaration4=typeDeclaration();

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration4.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop2;
                                }
                            } while (true);


                            }
                            break;
                        case 2 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:234:13: classOrInterfaceDeclaration ( typeDeclaration )*
                            {
                            pushFollow(FOLLOW_classOrInterfaceDeclaration_in_compilationUnit316);
                            classOrInterfaceDeclaration5=classOrInterfaceDeclaration();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceDeclaration5.getTree());

                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:234:41: ( typeDeclaration )*
                            loop3:
                            do {
                                int alt3=2;
                                int LA3_0 = input.LA(1);

                                if ( (LA3_0==CLASS||LA3_0==ENUM||LA3_0==SEMI||LA3_0==STATIC||LA3_0==78||LA3_0==83||LA3_0==96||LA3_0==103||(LA3_0 >= 108 && LA3_0 <= 110)||LA3_0==113) ) {
                                    alt3=1;
                                }


                                switch (alt3) {
                            	case 1 :
                            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:234:41: typeDeclaration
                            	    {
                            	    pushFollow(FOLLOW_typeDeclaration_in_compilationUnit318);
                            	    typeDeclaration6=typeDeclaration();

                            	    state._fsp--;
                            	    if (state.failed) return retval;
                            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration6.getTree());

                            	    }
                            	    break;

                            	default :
                            	    break loop3;
                                }
                            } while (true);


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:236:9: ( packageDeclaration )? ( importDeclaration )* ( typeDeclaration )*
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:236:9: ( packageDeclaration )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==PACKAGE) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:236:9: packageDeclaration
                            {
                            pushFollow(FOLLOW_packageDeclaration_in_compilationUnit339);
                            packageDeclaration7=packageDeclaration();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, packageDeclaration7.getTree());

                            }
                            break;

                    }


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:236:29: ( importDeclaration )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==IMPORT) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:236:29: importDeclaration
                    	    {
                    	    pushFollow(FOLLOW_importDeclaration_in_compilationUnit342);
                    	    importDeclaration8=importDeclaration();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, importDeclaration8.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:236:48: ( typeDeclaration )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==CLASS||LA7_0==ENUM||LA7_0==SEMI||LA7_0==STATIC||LA7_0==78||LA7_0==83||LA7_0==96||LA7_0==103||(LA7_0 >= 108 && LA7_0 <= 110)||LA7_0==113) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:236:48: typeDeclaration
                    	    {
                    	    pushFollow(FOLLOW_typeDeclaration_in_compilationUnit345);
                    	    typeDeclaration9=typeDeclaration();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeDeclaration9.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 1, compilationUnit_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "compilationUnit"


    public static class packageDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "packageDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:239:1: packageDeclaration : PACKAGE ^ qualifiedName SEMI !;
    public final JavaParser.packageDeclaration_return packageDeclaration() throws RecognitionException {
        JavaParser.packageDeclaration_return retval = new JavaParser.packageDeclaration_return();
        retval.start = input.LT(1);

        int packageDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        Token PACKAGE10=null;
        Token SEMI12=null;
        JavaParser.qualifiedName_return qualifiedName11 =null;


        CommonTree PACKAGE10_tree=null;
        CommonTree SEMI12_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:240:5: ( PACKAGE ^ qualifiedName SEMI !)
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:240:9: PACKAGE ^ qualifiedName SEMI !
            {
            root_0 = (CommonTree)adaptor.nil();


            PACKAGE10=(Token)match(input,PACKAGE,FOLLOW_PACKAGE_in_packageDeclaration365); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PACKAGE10_tree = 
            (CommonTree)adaptor.create(PACKAGE10)
            ;
            root_0 = (CommonTree)adaptor.becomeRoot(PACKAGE10_tree, root_0);
            }

            pushFollow(FOLLOW_qualifiedName_in_packageDeclaration368);
            qualifiedName11=qualifiedName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedName11.getTree());

            SEMI12=(Token)match(input,SEMI,FOLLOW_SEMI_in_packageDeclaration370); if (state.failed) return retval;

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 2, packageDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "packageDeclaration"


    public static class importDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "importDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:243:1: importDeclaration : ( IMPORT ^ ( STATIC )? qualifiedName SEMI !| IMPORT ( STATIC )? qualifiedName DOT STAR SEMI -> ^( IMPORT ( STATIC )? ^( DOT qualifiedName STAR ) ) );
    public final JavaParser.importDeclaration_return importDeclaration() throws RecognitionException {
        JavaParser.importDeclaration_return retval = new JavaParser.importDeclaration_return();
        retval.start = input.LT(1);

        int importDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        Token IMPORT13=null;
        Token STATIC14=null;
        Token SEMI16=null;
        Token IMPORT17=null;
        Token STATIC18=null;
        Token DOT20=null;
        Token STAR21=null;
        Token SEMI22=null;
        JavaParser.qualifiedName_return qualifiedName15 =null;

        JavaParser.qualifiedName_return qualifiedName19 =null;


        CommonTree IMPORT13_tree=null;
        CommonTree STATIC14_tree=null;
        CommonTree SEMI16_tree=null;
        CommonTree IMPORT17_tree=null;
        CommonTree STATIC18_tree=null;
        CommonTree DOT20_tree=null;
        CommonTree STAR21_tree=null;
        CommonTree SEMI22_tree=null;
        RewriteRuleTokenStream stream_IMPORT=new RewriteRuleTokenStream(adaptor,"token IMPORT");
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_STATIC=new RewriteRuleTokenStream(adaptor,"token STATIC");
        RewriteRuleTokenStream stream_SEMI=new RewriteRuleTokenStream(adaptor,"token SEMI");
        RewriteRuleSubtreeStream stream_qualifiedName=new RewriteRuleSubtreeStream(adaptor,"rule qualifiedName");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:244:5: ( IMPORT ^ ( STATIC )? qualifiedName SEMI !| IMPORT ( STATIC )? qualifiedName DOT STAR SEMI -> ^( IMPORT ( STATIC )? ^( DOT qualifiedName STAR ) ) )
            int alt11=2;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:244:9: IMPORT ^ ( STATIC )? qualifiedName SEMI !
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    IMPORT13=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_importDeclaration394); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    IMPORT13_tree = 
                    (CommonTree)adaptor.create(IMPORT13)
                    ;
                    root_0 = (CommonTree)adaptor.becomeRoot(IMPORT13_tree, root_0);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:244:17: ( STATIC )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==STATIC) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:244:17: STATIC
                            {
                            STATIC14=(Token)match(input,STATIC,FOLLOW_STATIC_in_importDeclaration397); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            STATIC14_tree = 
                            (CommonTree)adaptor.create(STATIC14)
                            ;
                            adaptor.addChild(root_0, STATIC14_tree);
                            }

                            }
                            break;

                    }


                    pushFollow(FOLLOW_qualifiedName_in_importDeclaration400);
                    qualifiedName15=qualifiedName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedName15.getTree());

                    SEMI16=(Token)match(input,SEMI,FOLLOW_SEMI_in_importDeclaration403); if (state.failed) return retval;

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:245:9: IMPORT ( STATIC )? qualifiedName DOT STAR SEMI
                    {
                    IMPORT17=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_importDeclaration414); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IMPORT.add(IMPORT17);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:245:16: ( STATIC )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==STATIC) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:245:16: STATIC
                            {
                            STATIC18=(Token)match(input,STATIC,FOLLOW_STATIC_in_importDeclaration416); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_STATIC.add(STATIC18);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_qualifiedName_in_importDeclaration419);
                    qualifiedName19=qualifiedName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_qualifiedName.add(qualifiedName19.getTree());

                    DOT20=(Token)match(input,DOT,FOLLOW_DOT_in_importDeclaration421); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOT.add(DOT20);


                    STAR21=(Token)match(input,STAR,FOLLOW_STAR_in_importDeclaration423); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STAR.add(STAR21);


                    SEMI22=(Token)match(input,SEMI,FOLLOW_SEMI_in_importDeclaration425); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMI.add(SEMI22);


                    // AST REWRITE
                    // elements: IMPORT, STATIC, DOT, STAR, qualifiedName
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 245:52: -> ^( IMPORT ( STATIC )? ^( DOT qualifiedName STAR ) )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:245:55: ^( IMPORT ( STATIC )? ^( DOT qualifiedName STAR ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_IMPORT.nextNode()
                        , root_1);

                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:245:64: ( STATIC )?
                        if ( stream_STATIC.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_STATIC.nextNode()
                            );

                        }
                        stream_STATIC.reset();

                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:245:72: ^( DOT qualifiedName STAR )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(
                        stream_DOT.nextNode()
                        , root_2);

                        adaptor.addChild(root_2, stream_qualifiedName.nextTree());

                        adaptor.addChild(root_2, 
                        stream_STAR.nextNode()
                        );

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 3, importDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "importDeclaration"


    public static class typeDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:248:1: typeDeclaration : ( classOrInterfaceDeclaration | ';' );
    public final JavaParser.typeDeclaration_return typeDeclaration() throws RecognitionException {
        JavaParser.typeDeclaration_return retval = new JavaParser.typeDeclaration_return();
        retval.start = input.LT(1);

        int typeDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal24=null;
        JavaParser.classOrInterfaceDeclaration_return classOrInterfaceDeclaration23 =null;


        CommonTree char_literal24_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:249:5: ( classOrInterfaceDeclaration | ';' )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==CLASS||LA12_0==ENUM||LA12_0==STATIC||LA12_0==78||LA12_0==83||LA12_0==96||LA12_0==103||(LA12_0 >= 108 && LA12_0 <= 110)||LA12_0==113) ) {
                alt12=1;
            }
            else if ( (LA12_0==SEMI) ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;

            }
            switch (alt12) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:249:9: classOrInterfaceDeclaration
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_classOrInterfaceDeclaration_in_typeDeclaration465);
                    classOrInterfaceDeclaration23=classOrInterfaceDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceDeclaration23.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:250:9: ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal24=(Token)match(input,SEMI,FOLLOW_SEMI_in_typeDeclaration475); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal24_tree = 
                    (CommonTree)adaptor.create(char_literal24)
                    ;
                    adaptor.addChild(root_0, char_literal24_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 4, typeDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "typeDeclaration"


    public static class classOrInterfaceDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "classOrInterfaceDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:253:1: classOrInterfaceDeclaration : classOrInterfaceModifiers ! ( classDeclaration[$classOrInterfaceModifiers.tree] | interfaceDeclaration[$classOrInterfaceModifiers.tree] ) ;
    public final JavaParser.classOrInterfaceDeclaration_return classOrInterfaceDeclaration() throws RecognitionException {
        JavaParser.classOrInterfaceDeclaration_return retval = new JavaParser.classOrInterfaceDeclaration_return();
        retval.start = input.LT(1);

        int classOrInterfaceDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.classOrInterfaceModifiers_return classOrInterfaceModifiers25 =null;

        JavaParser.classDeclaration_return classDeclaration26 =null;

        JavaParser.interfaceDeclaration_return interfaceDeclaration27 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:254:5: ( classOrInterfaceModifiers ! ( classDeclaration[$classOrInterfaceModifiers.tree] | interfaceDeclaration[$classOrInterfaceModifiers.tree] ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:254:9: classOrInterfaceModifiers ! ( classDeclaration[$classOrInterfaceModifiers.tree] | interfaceDeclaration[$classOrInterfaceModifiers.tree] )
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_classOrInterfaceModifiers_in_classOrInterfaceDeclaration498);
            classOrInterfaceModifiers25=classOrInterfaceModifiers();

            state._fsp--;
            if (state.failed) return retval;

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:254:36: ( classDeclaration[$classOrInterfaceModifiers.tree] | interfaceDeclaration[$classOrInterfaceModifiers.tree] )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==CLASS||LA13_0==ENUM) ) {
                alt13=1;
            }
            else if ( (LA13_0==78||LA13_0==103) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }
            switch (alt13) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:254:37: classDeclaration[$classOrInterfaceModifiers.tree]
                    {
                    pushFollow(FOLLOW_classDeclaration_in_classOrInterfaceDeclaration502);
                    classDeclaration26=classDeclaration((classOrInterfaceModifiers25!=null?((CommonTree)classOrInterfaceModifiers25.tree):null));

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classDeclaration26.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:254:89: interfaceDeclaration[$classOrInterfaceModifiers.tree]
                    {
                    pushFollow(FOLLOW_interfaceDeclaration_in_classOrInterfaceDeclaration507);
                    interfaceDeclaration27=interfaceDeclaration((classOrInterfaceModifiers25!=null?((CommonTree)classOrInterfaceModifiers25.tree):null));

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceDeclaration27.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 5, classOrInterfaceDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "classOrInterfaceDeclaration"


    public static class classOrInterfaceModifiers_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "classOrInterfaceModifiers"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:257:1: classOrInterfaceModifiers : ( classOrInterfaceModifier )* -> ^( MODIFIER_LIST ( classOrInterfaceModifier )* ) ;
    public final JavaParser.classOrInterfaceModifiers_return classOrInterfaceModifiers() throws RecognitionException {
        JavaParser.classOrInterfaceModifiers_return retval = new JavaParser.classOrInterfaceModifiers_return();
        retval.start = input.LT(1);

        int classOrInterfaceModifiers_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.classOrInterfaceModifier_return classOrInterfaceModifier28 =null;


        RewriteRuleSubtreeStream stream_classOrInterfaceModifier=new RewriteRuleSubtreeStream(adaptor,"rule classOrInterfaceModifier");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:258:5: ( ( classOrInterfaceModifier )* -> ^( MODIFIER_LIST ( classOrInterfaceModifier )* ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:258:9: ( classOrInterfaceModifier )*
            {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:258:9: ( classOrInterfaceModifier )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==78) ) {
                    int LA14_2 = input.LA(2);

                    if ( (LA14_2==Identifier) ) {
                        alt14=1;
                    }


                }
                else if ( (LA14_0==STATIC||LA14_0==83||LA14_0==96||(LA14_0 >= 108 && LA14_0 <= 110)||LA14_0==113) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:258:9: classOrInterfaceModifier
            	    {
            	    pushFollow(FOLLOW_classOrInterfaceModifier_in_classOrInterfaceModifiers532);
            	    classOrInterfaceModifier28=classOrInterfaceModifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_classOrInterfaceModifier.add(classOrInterfaceModifier28.getTree());

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            // AST REWRITE
            // elements: classOrInterfaceModifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 258:35: -> ^( MODIFIER_LIST ( classOrInterfaceModifier )* )
            {
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:258:38: ^( MODIFIER_LIST ( classOrInterfaceModifier )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(MODIFIER_LIST, "MODIFIER_LIST")
                , root_1);

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:258:54: ( classOrInterfaceModifier )*
                while ( stream_classOrInterfaceModifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_classOrInterfaceModifier.nextTree());

                }
                stream_classOrInterfaceModifier.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 6, classOrInterfaceModifiers_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "classOrInterfaceModifiers"


    public static class classOrInterfaceModifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "classOrInterfaceModifier"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:261:1: classOrInterfaceModifier : ( annotation | 'public' | 'protected' | 'private' | 'abstract' | STATIC | 'final' | 'strictfp' );
    public final JavaParser.classOrInterfaceModifier_return classOrInterfaceModifier() throws RecognitionException {
        JavaParser.classOrInterfaceModifier_return retval = new JavaParser.classOrInterfaceModifier_return();
        retval.start = input.LT(1);

        int classOrInterfaceModifier_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal30=null;
        Token string_literal31=null;
        Token string_literal32=null;
        Token string_literal33=null;
        Token STATIC34=null;
        Token string_literal35=null;
        Token string_literal36=null;
        JavaParser.annotation_return annotation29 =null;


        CommonTree string_literal30_tree=null;
        CommonTree string_literal31_tree=null;
        CommonTree string_literal32_tree=null;
        CommonTree string_literal33_tree=null;
        CommonTree STATIC34_tree=null;
        CommonTree string_literal35_tree=null;
        CommonTree string_literal36_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:262:5: ( annotation | 'public' | 'protected' | 'private' | 'abstract' | STATIC | 'final' | 'strictfp' )
            int alt15=8;
            switch ( input.LA(1) ) {
            case 78:
                {
                alt15=1;
                }
                break;
            case 110:
                {
                alt15=2;
                }
                break;
            case 109:
                {
                alt15=3;
                }
                break;
            case 108:
                {
                alt15=4;
                }
                break;
            case 83:
                {
                alt15=5;
                }
                break;
            case STATIC:
                {
                alt15=6;
                }
                break;
            case 96:
                {
                alt15=7;
                }
                break;
            case 113:
                {
                alt15=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }

            switch (alt15) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:262:9: annotation
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_annotation_in_classOrInterfaceModifier561);
                    annotation29=annotation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation29.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:263:9: 'public'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal30=(Token)match(input,110,FOLLOW_110_in_classOrInterfaceModifier574); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal30_tree = 
                    (CommonTree)adaptor.create(string_literal30)
                    ;
                    adaptor.addChild(root_0, string_literal30_tree);
                    }

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:264:9: 'protected'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal31=(Token)match(input,109,FOLLOW_109_in_classOrInterfaceModifier589); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal31_tree = 
                    (CommonTree)adaptor.create(string_literal31)
                    ;
                    adaptor.addChild(root_0, string_literal31_tree);
                    }

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:265:9: 'private'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal32=(Token)match(input,108,FOLLOW_108_in_classOrInterfaceModifier601); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal32_tree = 
                    (CommonTree)adaptor.create(string_literal32)
                    ;
                    adaptor.addChild(root_0, string_literal32_tree);
                    }

                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:266:9: 'abstract'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal33=(Token)match(input,83,FOLLOW_83_in_classOrInterfaceModifier615); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal33_tree = 
                    (CommonTree)adaptor.create(string_literal33)
                    ;
                    adaptor.addChild(root_0, string_literal33_tree);
                    }

                    }
                    break;
                case 6 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:267:10: STATIC
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    STATIC34=(Token)match(input,STATIC,FOLLOW_STATIC_in_classOrInterfaceModifier629); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STATIC34_tree = 
                    (CommonTree)adaptor.create(STATIC34)
                    ;
                    adaptor.addChild(root_0, STATIC34_tree);
                    }

                    }
                    break;
                case 7 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:268:9: 'final'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal35=(Token)match(input,96,FOLLOW_96_in_classOrInterfaceModifier644); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal35_tree = 
                    (CommonTree)adaptor.create(string_literal35)
                    ;
                    adaptor.addChild(root_0, string_literal35_tree);
                    }

                    }
                    break;
                case 8 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:269:9: 'strictfp'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal36=(Token)match(input,113,FOLLOW_113_in_classOrInterfaceModifier660); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal36_tree = 
                    (CommonTree)adaptor.create(string_literal36)
                    ;
                    adaptor.addChild(root_0, string_literal36_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 7, classOrInterfaceModifier_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "classOrInterfaceModifier"


    public static class modifiers_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "modifiers"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:272:1: modifiers : ( modifier )* -> ^( MODIFIER_LIST ( modifier )* ) ;
    public final JavaParser.modifiers_return modifiers() throws RecognitionException {
        JavaParser.modifiers_return retval = new JavaParser.modifiers_return();
        retval.start = input.LT(1);

        int modifiers_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.modifier_return modifier37 =null;


        RewriteRuleSubtreeStream stream_modifier=new RewriteRuleSubtreeStream(adaptor,"rule modifier");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:273:5: ( ( modifier )* -> ^( MODIFIER_LIST ( modifier )* ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:273:9: ( modifier )*
            {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:273:9: ( modifier )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==78) ) {
                    int LA16_2 = input.LA(2);

                    if ( (LA16_2==Identifier) ) {
                        alt16=1;
                    }


                }
                else if ( (LA16_0==STATIC||LA16_0==83||LA16_0==96||LA16_0==105||(LA16_0 >= 108 && LA16_0 <= 110)||LA16_0==113||LA16_0==115||LA16_0==119||LA16_0==122) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:273:9: modifier
            	    {
            	    pushFollow(FOLLOW_modifier_in_modifiers682);
            	    modifier37=modifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_modifier.add(modifier37.getTree());

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            // AST REWRITE
            // elements: modifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 273:19: -> ^( MODIFIER_LIST ( modifier )* )
            {
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:273:22: ^( MODIFIER_LIST ( modifier )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(MODIFIER_LIST, "MODIFIER_LIST")
                , root_1);

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:273:38: ( modifier )*
                while ( stream_modifier.hasNext() ) {
                    adaptor.addChild(root_1, stream_modifier.nextTree());

                }
                stream_modifier.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, modifiers_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "modifiers"


    public static class classDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "classDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:276:1: classDeclaration[CommonTree modifiers] : ( normalClassDeclaration[$modifiers] | enumDeclaration[$modifiers] );
    public final JavaParser.classDeclaration_return classDeclaration(CommonTree modifiers) throws RecognitionException {
        JavaParser.classDeclaration_return retval = new JavaParser.classDeclaration_return();
        retval.start = input.LT(1);

        int classDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.normalClassDeclaration_return normalClassDeclaration38 =null;

        JavaParser.enumDeclaration_return enumDeclaration39 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:277:5: ( normalClassDeclaration[$modifiers] | enumDeclaration[$modifiers] )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==CLASS) ) {
                alt17=1;
            }
            else if ( (LA17_0==ENUM) ) {
                alt17=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;

            }
            switch (alt17) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:277:9: normalClassDeclaration[$modifiers]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_normalClassDeclaration_in_classDeclaration712);
                    normalClassDeclaration38=normalClassDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, normalClassDeclaration38.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:278:9: enumDeclaration[$modifiers]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_enumDeclaration_in_classDeclaration723);
                    enumDeclaration39=enumDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumDeclaration39.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 9, classDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "classDeclaration"


    public static class normalClassDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "normalClassDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:281:1: normalClassDeclaration[CommonTree modifiers] : CLASS Identifier ( typeParameters )? ( EXTENDS type )? ( IMPLEMENTS typeList )? classBody -> ^( CLASS_DECLARATION Identifier ( typeParameters )? ( ^( EXTENDS type ) )? ( ^( IMPLEMENTS typeList ) )? classBody ) ;
    public final JavaParser.normalClassDeclaration_return normalClassDeclaration(CommonTree modifiers) throws RecognitionException {
        JavaParser.normalClassDeclaration_return retval = new JavaParser.normalClassDeclaration_return();
        retval.start = input.LT(1);

        int normalClassDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        Token CLASS40=null;
        Token Identifier41=null;
        Token EXTENDS43=null;
        Token IMPLEMENTS45=null;
        JavaParser.typeParameters_return typeParameters42 =null;

        JavaParser.type_return type44 =null;

        JavaParser.typeList_return typeList46 =null;

        JavaParser.classBody_return classBody47 =null;


        CommonTree CLASS40_tree=null;
        CommonTree Identifier41_tree=null;
        CommonTree EXTENDS43_tree=null;
        CommonTree IMPLEMENTS45_tree=null;
        RewriteRuleTokenStream stream_CLASS=new RewriteRuleTokenStream(adaptor,"token CLASS");
        RewriteRuleTokenStream stream_IMPLEMENTS=new RewriteRuleTokenStream(adaptor,"token IMPLEMENTS");
        RewriteRuleTokenStream stream_EXTENDS=new RewriteRuleTokenStream(adaptor,"token EXTENDS");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_typeParameters=new RewriteRuleSubtreeStream(adaptor,"rule typeParameters");
        RewriteRuleSubtreeStream stream_classBody=new RewriteRuleSubtreeStream(adaptor,"rule classBody");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        RewriteRuleSubtreeStream stream_typeList=new RewriteRuleSubtreeStream(adaptor,"rule typeList");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:282:5: ( CLASS Identifier ( typeParameters )? ( EXTENDS type )? ( IMPLEMENTS typeList )? classBody -> ^( CLASS_DECLARATION Identifier ( typeParameters )? ( ^( EXTENDS type ) )? ( ^( IMPLEMENTS typeList ) )? classBody ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:282:9: CLASS Identifier ( typeParameters )? ( EXTENDS type )? ( IMPLEMENTS typeList )? classBody
            {
            CLASS40=(Token)match(input,CLASS,FOLLOW_CLASS_in_normalClassDeclaration749); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CLASS.add(CLASS40);


            Identifier41=(Token)match(input,Identifier,FOLLOW_Identifier_in_normalClassDeclaration751); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier41);


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:282:26: ( typeParameters )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==LS) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:282:26: typeParameters
                    {
                    pushFollow(FOLLOW_typeParameters_in_normalClassDeclaration753);
                    typeParameters42=typeParameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeParameters.add(typeParameters42.getTree());

                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:283:9: ( EXTENDS type )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==EXTENDS) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:283:10: EXTENDS type
                    {
                    EXTENDS43=(Token)match(input,EXTENDS,FOLLOW_EXTENDS_in_normalClassDeclaration765); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_EXTENDS.add(EXTENDS43);


                    pushFollow(FOLLOW_type_in_normalClassDeclaration767);
                    type44=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_type.add(type44.getTree());

                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:284:9: ( IMPLEMENTS typeList )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==IMPLEMENTS) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:284:10: IMPLEMENTS typeList
                    {
                    IMPLEMENTS45=(Token)match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_normalClassDeclaration780); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IMPLEMENTS.add(IMPLEMENTS45);


                    pushFollow(FOLLOW_typeList_in_normalClassDeclaration782);
                    typeList46=typeList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_typeList.add(typeList46.getTree());

                    }
                    break;

            }


            pushFollow(FOLLOW_classBody_in_normalClassDeclaration794);
            classBody47=classBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_classBody.add(classBody47.getTree());

            // AST REWRITE
            // elements: Identifier, EXTENDS, IMPLEMENTS, classBody, type, typeParameters, typeList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 285:19: -> ^( CLASS_DECLARATION Identifier ( typeParameters )? ( ^( EXTENDS type ) )? ( ^( IMPLEMENTS typeList ) )? classBody )
            {
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:285:22: ^( CLASS_DECLARATION Identifier ( typeParameters )? ( ^( EXTENDS type ) )? ( ^( IMPLEMENTS typeList ) )? classBody )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(CLASS_DECLARATION, "CLASS_DECLARATION")
                , root_1);

                adaptor.addChild(root_1, 
                stream_Identifier.nextNode()
                );

                adaptor.addChild(root_1, modifiers);

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:285:66: ( typeParameters )?
                if ( stream_typeParameters.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeParameters.nextTree());

                }
                stream_typeParameters.reset();

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:285:82: ( ^( EXTENDS type ) )?
                if ( stream_EXTENDS.hasNext()||stream_type.hasNext() ) {
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:285:82: ^( EXTENDS type )
                    {
                    CommonTree root_2 = (CommonTree)adaptor.nil();
                    root_2 = (CommonTree)adaptor.becomeRoot(
                    stream_EXTENDS.nextNode()
                    , root_2);

                    adaptor.addChild(root_2, stream_type.nextTree());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_EXTENDS.reset();
                stream_type.reset();

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:285:99: ( ^( IMPLEMENTS typeList ) )?
                if ( stream_IMPLEMENTS.hasNext()||stream_typeList.hasNext() ) {
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:285:99: ^( IMPLEMENTS typeList )
                    {
                    CommonTree root_2 = (CommonTree)adaptor.nil();
                    root_2 = (CommonTree)adaptor.becomeRoot(
                    stream_IMPLEMENTS.nextNode()
                    , root_2);

                    adaptor.addChild(root_2, stream_typeList.nextTree());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_IMPLEMENTS.reset();
                stream_typeList.reset();

                adaptor.addChild(root_1, stream_classBody.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 10, normalClassDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "normalClassDeclaration"


    public static class typeParameters_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeParameters"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:288:1: typeParameters : '<' typeParameter ( ',' typeParameter )* '>' -> ^( TYPE_PARAMETERS ( typeParameter )+ ) ;
    public final JavaParser.typeParameters_return typeParameters() throws RecognitionException {
        JavaParser.typeParameters_return retval = new JavaParser.typeParameters_return();
        retval.start = input.LT(1);

        int typeParameters_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal48=null;
        Token char_literal50=null;
        Token char_literal52=null;
        JavaParser.typeParameter_return typeParameter49 =null;

        JavaParser.typeParameter_return typeParameter51 =null;


        CommonTree char_literal48_tree=null;
        CommonTree char_literal50_tree=null;
        CommonTree char_literal52_tree=null;
        RewriteRuleTokenStream stream_GT=new RewriteRuleTokenStream(adaptor,"token GT");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_LS=new RewriteRuleTokenStream(adaptor,"token LS");
        RewriteRuleSubtreeStream stream_typeParameter=new RewriteRuleSubtreeStream(adaptor,"rule typeParameter");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:289:5: ( '<' typeParameter ( ',' typeParameter )* '>' -> ^( TYPE_PARAMETERS ( typeParameter )+ ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:289:9: '<' typeParameter ( ',' typeParameter )* '>'
            {
            char_literal48=(Token)match(input,LS,FOLLOW_LS_in_typeParameters846); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_LS.add(char_literal48);


            pushFollow(FOLLOW_typeParameter_in_typeParameters848);
            typeParameter49=typeParameter();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_typeParameter.add(typeParameter49.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:289:27: ( ',' typeParameter )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==68) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:289:28: ',' typeParameter
            	    {
            	    char_literal50=(Token)match(input,68,FOLLOW_68_in_typeParameters851); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_68.add(char_literal50);


            	    pushFollow(FOLLOW_typeParameter_in_typeParameters853);
            	    typeParameter51=typeParameter();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_typeParameter.add(typeParameter51.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            char_literal52=(Token)match(input,GT,FOLLOW_GT_in_typeParameters857); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_GT.add(char_literal52);


            // AST REWRITE
            // elements: typeParameter
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 289:52: -> ^( TYPE_PARAMETERS ( typeParameter )+ )
            {
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:289:55: ^( TYPE_PARAMETERS ( typeParameter )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(TYPE_PARAMETERS, "TYPE_PARAMETERS")
                , root_1);

                if ( !(stream_typeParameter.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_typeParameter.hasNext() ) {
                    adaptor.addChild(root_1, stream_typeParameter.nextTree());

                }
                stream_typeParameter.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, typeParameters_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "typeParameters"


    public static class typeParameter_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeParameter"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:292:1: typeParameter : Identifier ( EXTENDS typeBound )? ;
    public final JavaParser.typeParameter_return typeParameter() throws RecognitionException {
        JavaParser.typeParameter_return retval = new JavaParser.typeParameter_return();
        retval.start = input.LT(1);

        int typeParameter_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier53=null;
        Token EXTENDS54=null;
        JavaParser.typeBound_return typeBound55 =null;


        CommonTree Identifier53_tree=null;
        CommonTree EXTENDS54_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:293:5: ( Identifier ( EXTENDS typeBound )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:293:9: Identifier ( EXTENDS typeBound )?
            {
            root_0 = (CommonTree)adaptor.nil();


            Identifier53=(Token)match(input,Identifier,FOLLOW_Identifier_in_typeParameter885); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier53_tree = 
            (CommonTree)adaptor.create(Identifier53)
            ;
            adaptor.addChild(root_0, Identifier53_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:293:20: ( EXTENDS typeBound )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==EXTENDS) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:293:21: EXTENDS typeBound
                    {
                    EXTENDS54=(Token)match(input,EXTENDS,FOLLOW_EXTENDS_in_typeParameter888); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EXTENDS54_tree = 
                    (CommonTree)adaptor.create(EXTENDS54)
                    ;
                    adaptor.addChild(root_0, EXTENDS54_tree);
                    }

                    pushFollow(FOLLOW_typeBound_in_typeParameter890);
                    typeBound55=typeBound();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeBound55.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 12, typeParameter_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "typeParameter"


    public static class typeBound_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeBound"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:296:1: typeBound : type ( '&' type )* ;
    public final JavaParser.typeBound_return typeBound() throws RecognitionException {
        JavaParser.typeBound_return retval = new JavaParser.typeBound_return();
        retval.start = input.LT(1);

        int typeBound_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal57=null;
        JavaParser.type_return type56 =null;

        JavaParser.type_return type58 =null;


        CommonTree char_literal57_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:297:5: ( type ( '&' type )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:297:9: type ( '&' type )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_type_in_typeBound919);
            type56=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type56.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:297:14: ( '&' type )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==60) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:297:15: '&' type
            	    {
            	    char_literal57=(Token)match(input,60,FOLLOW_60_in_typeBound922); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal57_tree = 
            	    (CommonTree)adaptor.create(char_literal57)
            	    ;
            	    adaptor.addChild(root_0, char_literal57_tree);
            	    }

            	    pushFollow(FOLLOW_type_in_typeBound924);
            	    type58=type();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, type58.getTree());

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, typeBound_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "typeBound"


    public static class enumDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "enumDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:300:1: enumDeclaration[CommonTree modifiers] : ENUM Identifier ( 'implements' typeList )? enumBody ;
    public final JavaParser.enumDeclaration_return enumDeclaration(CommonTree modifiers) throws RecognitionException {
        JavaParser.enumDeclaration_return retval = new JavaParser.enumDeclaration_return();
        retval.start = input.LT(1);

        int enumDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        Token ENUM59=null;
        Token Identifier60=null;
        Token string_literal61=null;
        JavaParser.typeList_return typeList62 =null;

        JavaParser.enumBody_return enumBody63 =null;


        CommonTree ENUM59_tree=null;
        CommonTree Identifier60_tree=null;
        CommonTree string_literal61_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:301:5: ( ENUM Identifier ( 'implements' typeList )? enumBody )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:301:9: ENUM Identifier ( 'implements' typeList )? enumBody
            {
            root_0 = (CommonTree)adaptor.nil();


            ENUM59=(Token)match(input,ENUM,FOLLOW_ENUM_in_enumDeclaration947); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ENUM59_tree = 
            (CommonTree)adaptor.create(ENUM59)
            ;
            adaptor.addChild(root_0, ENUM59_tree);
            }

            Identifier60=(Token)match(input,Identifier,FOLLOW_Identifier_in_enumDeclaration949); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier60_tree = 
            (CommonTree)adaptor.create(Identifier60)
            ;
            adaptor.addChild(root_0, Identifier60_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:301:25: ( 'implements' typeList )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==IMPLEMENTS) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:301:26: 'implements' typeList
                    {
                    string_literal61=(Token)match(input,IMPLEMENTS,FOLLOW_IMPLEMENTS_in_enumDeclaration952); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal61_tree = 
                    (CommonTree)adaptor.create(string_literal61)
                    ;
                    adaptor.addChild(root_0, string_literal61_tree);
                    }

                    pushFollow(FOLLOW_typeList_in_enumDeclaration954);
                    typeList62=typeList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeList62.getTree());

                    }
                    break;

            }


            pushFollow(FOLLOW_enumBody_in_enumDeclaration958);
            enumBody63=enumBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, enumBody63.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, enumDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "enumDeclaration"


    public static class enumBody_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "enumBody"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:304:1: enumBody : '{' ( enumConstants )? ( ',' )? ( enumBodyDeclarations )? '}' ;
    public final JavaParser.enumBody_return enumBody() throws RecognitionException {
        JavaParser.enumBody_return retval = new JavaParser.enumBody_return();
        retval.start = input.LT(1);

        int enumBody_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal64=null;
        Token char_literal66=null;
        Token char_literal68=null;
        JavaParser.enumConstants_return enumConstants65 =null;

        JavaParser.enumBodyDeclarations_return enumBodyDeclarations67 =null;


        CommonTree char_literal64_tree=null;
        CommonTree char_literal66_tree=null;
        CommonTree char_literal68_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:305:5: ( '{' ( enumConstants )? ( ',' )? ( enumBodyDeclarations )? '}' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:305:9: '{' ( enumConstants )? ( ',' )? ( enumBodyDeclarations )? '}'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal64=(Token)match(input,124,FOLLOW_124_in_enumBody977); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal64_tree = 
            (CommonTree)adaptor.create(char_literal64)
            ;
            adaptor.addChild(root_0, char_literal64_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:305:13: ( enumConstants )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==Identifier||LA25_0==78) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:305:13: enumConstants
                    {
                    pushFollow(FOLLOW_enumConstants_in_enumBody979);
                    enumConstants65=enumConstants();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumConstants65.getTree());

                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:305:28: ( ',' )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==68) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:305:28: ','
                    {
                    char_literal66=(Token)match(input,68,FOLLOW_68_in_enumBody982); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal66_tree = 
                    (CommonTree)adaptor.create(char_literal66)
                    ;
                    adaptor.addChild(root_0, char_literal66_tree);
                    }

                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:305:33: ( enumBodyDeclarations )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==SEMI) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:305:33: enumBodyDeclarations
                    {
                    pushFollow(FOLLOW_enumBodyDeclarations_in_enumBody985);
                    enumBodyDeclarations67=enumBodyDeclarations();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumBodyDeclarations67.getTree());

                    }
                    break;

            }


            char_literal68=(Token)match(input,128,FOLLOW_128_in_enumBody988); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal68_tree = 
            (CommonTree)adaptor.create(char_literal68)
            ;
            adaptor.addChild(root_0, char_literal68_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, enumBody_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "enumBody"


    public static class enumConstants_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "enumConstants"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:308:1: enumConstants : enumConstant ( ',' enumConstant )* ;
    public final JavaParser.enumConstants_return enumConstants() throws RecognitionException {
        JavaParser.enumConstants_return retval = new JavaParser.enumConstants_return();
        retval.start = input.LT(1);

        int enumConstants_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal70=null;
        JavaParser.enumConstant_return enumConstant69 =null;

        JavaParser.enumConstant_return enumConstant71 =null;


        CommonTree char_literal70_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:309:5: ( enumConstant ( ',' enumConstant )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:309:9: enumConstant ( ',' enumConstant )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_enumConstant_in_enumConstants1007);
            enumConstant69=enumConstant();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, enumConstant69.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:309:22: ( ',' enumConstant )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==68) ) {
                    int LA28_1 = input.LA(2);

                    if ( (LA28_1==Identifier||LA28_1==78) ) {
                        alt28=1;
                    }


                }


                switch (alt28) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:309:23: ',' enumConstant
            	    {
            	    char_literal70=(Token)match(input,68,FOLLOW_68_in_enumConstants1010); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal70_tree = 
            	    (CommonTree)adaptor.create(char_literal70)
            	    ;
            	    adaptor.addChild(root_0, char_literal70_tree);
            	    }

            	    pushFollow(FOLLOW_enumConstant_in_enumConstants1012);
            	    enumConstant71=enumConstant();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumConstant71.getTree());

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 16, enumConstants_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "enumConstants"


    public static class enumConstant_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "enumConstant"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:312:1: enumConstant : ( annotations )? Identifier ( arguments )? ( classBody )? ;
    public final JavaParser.enumConstant_return enumConstant() throws RecognitionException {
        JavaParser.enumConstant_return retval = new JavaParser.enumConstant_return();
        retval.start = input.LT(1);

        int enumConstant_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier73=null;
        JavaParser.annotations_return annotations72 =null;

        JavaParser.arguments_return arguments74 =null;

        JavaParser.classBody_return classBody75 =null;


        CommonTree Identifier73_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:313:5: ( ( annotations )? Identifier ( arguments )? ( classBody )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:313:9: ( annotations )? Identifier ( arguments )? ( classBody )?
            {
            root_0 = (CommonTree)adaptor.nil();


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:313:9: ( annotations )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==78) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:313:9: annotations
                    {
                    pushFollow(FOLLOW_annotations_in_enumConstant1037);
                    annotations72=annotations();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotations72.getTree());

                    }
                    break;

            }


            Identifier73=(Token)match(input,Identifier,FOLLOW_Identifier_in_enumConstant1040); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier73_tree = 
            (CommonTree)adaptor.create(Identifier73)
            ;
            adaptor.addChild(root_0, Identifier73_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:313:33: ( arguments )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==62) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:313:33: arguments
                    {
                    pushFollow(FOLLOW_arguments_in_enumConstant1042);
                    arguments74=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments74.getTree());

                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:313:44: ( classBody )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==124) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:313:44: classBody
                    {
                    pushFollow(FOLLOW_classBody_in_enumConstant1045);
                    classBody75=classBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classBody75.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 17, enumConstant_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "enumConstant"


    public static class enumBodyDeclarations_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "enumBodyDeclarations"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:316:1: enumBodyDeclarations : ';' ( classBodyDeclaration )* ;
    public final JavaParser.enumBodyDeclarations_return enumBodyDeclarations() throws RecognitionException {
        JavaParser.enumBodyDeclarations_return retval = new JavaParser.enumBodyDeclarations_return();
        retval.start = input.LT(1);

        int enumBodyDeclarations_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal76=null;
        JavaParser.classBodyDeclaration_return classBodyDeclaration77 =null;


        CommonTree char_literal76_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:317:5: ( ';' ( classBodyDeclaration )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:317:9: ';' ( classBodyDeclaration )*
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal76=(Token)match(input,SEMI,FOLLOW_SEMI_in_enumBodyDeclarations1069); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal76_tree = 
            (CommonTree)adaptor.create(char_literal76)
            ;
            adaptor.addChild(root_0, char_literal76_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:317:13: ( classBodyDeclaration )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==CLASS||LA32_0==ENUM||LA32_0==Identifier||LA32_0==LS||LA32_0==SEMI||LA32_0==STATIC||LA32_0==VOID||LA32_0==78||(LA32_0 >= 83 && LA32_0 <= 84)||LA32_0==86||LA32_0==89||LA32_0==93||LA32_0==96||LA32_0==98||(LA32_0 >= 102 && LA32_0 <= 105)||(LA32_0 >= 108 && LA32_0 <= 110)||(LA32_0 >= 112 && LA32_0 <= 113)||LA32_0==115||LA32_0==119||LA32_0==122||LA32_0==124) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:317:14: classBodyDeclaration
            	    {
            	    pushFollow(FOLLOW_classBodyDeclaration_in_enumBodyDeclarations1072);
            	    classBodyDeclaration77=classBodyDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, classBodyDeclaration77.getTree());

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 18, enumBodyDeclarations_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "enumBodyDeclarations"


    public static class interfaceDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interfaceDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:320:1: interfaceDeclaration[CommonTree modifiers] : ( normalInterfaceDeclaration[$modifiers] | annotationTypeDeclaration[$modifiers] );
    public final JavaParser.interfaceDeclaration_return interfaceDeclaration(CommonTree modifiers) throws RecognitionException {
        JavaParser.interfaceDeclaration_return retval = new JavaParser.interfaceDeclaration_return();
        retval.start = input.LT(1);

        int interfaceDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.normalInterfaceDeclaration_return normalInterfaceDeclaration78 =null;

        JavaParser.annotationTypeDeclaration_return annotationTypeDeclaration79 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:321:5: ( normalInterfaceDeclaration[$modifiers] | annotationTypeDeclaration[$modifiers] )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==103) ) {
                alt33=1;
            }
            else if ( (LA33_0==78) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;

            }
            switch (alt33) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:321:9: normalInterfaceDeclaration[$modifiers]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_normalInterfaceDeclaration_in_interfaceDeclaration1098);
                    normalInterfaceDeclaration78=normalInterfaceDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, normalInterfaceDeclaration78.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:322:9: annotationTypeDeclaration[$modifiers]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_annotationTypeDeclaration_in_interfaceDeclaration1109);
                    annotationTypeDeclaration79=annotationTypeDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationTypeDeclaration79.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 19, interfaceDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "interfaceDeclaration"


    public static class normalInterfaceDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "normalInterfaceDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:325:1: normalInterfaceDeclaration[CommonTree modifiers] : 'interface' Identifier ( typeParameters )? ( EXTENDS typeList )? interfaceBody ;
    public final JavaParser.normalInterfaceDeclaration_return normalInterfaceDeclaration(CommonTree modifiers) throws RecognitionException {
        JavaParser.normalInterfaceDeclaration_return retval = new JavaParser.normalInterfaceDeclaration_return();
        retval.start = input.LT(1);

        int normalInterfaceDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal80=null;
        Token Identifier81=null;
        Token EXTENDS83=null;
        JavaParser.typeParameters_return typeParameters82 =null;

        JavaParser.typeList_return typeList84 =null;

        JavaParser.interfaceBody_return interfaceBody85 =null;


        CommonTree string_literal80_tree=null;
        CommonTree Identifier81_tree=null;
        CommonTree EXTENDS83_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:326:5: ( 'interface' Identifier ( typeParameters )? ( EXTENDS typeList )? interfaceBody )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:326:9: 'interface' Identifier ( typeParameters )? ( EXTENDS typeList )? interfaceBody
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal80=(Token)match(input,103,FOLLOW_103_in_normalInterfaceDeclaration1135); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal80_tree = 
            (CommonTree)adaptor.create(string_literal80)
            ;
            adaptor.addChild(root_0, string_literal80_tree);
            }

            Identifier81=(Token)match(input,Identifier,FOLLOW_Identifier_in_normalInterfaceDeclaration1137); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier81_tree = 
            (CommonTree)adaptor.create(Identifier81)
            ;
            adaptor.addChild(root_0, Identifier81_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:326:32: ( typeParameters )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==LS) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:326:32: typeParameters
                    {
                    pushFollow(FOLLOW_typeParameters_in_normalInterfaceDeclaration1139);
                    typeParameters82=typeParameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeParameters82.getTree());

                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:326:48: ( EXTENDS typeList )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==EXTENDS) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:326:49: EXTENDS typeList
                    {
                    EXTENDS83=(Token)match(input,EXTENDS,FOLLOW_EXTENDS_in_normalInterfaceDeclaration1143); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EXTENDS83_tree = 
                    (CommonTree)adaptor.create(EXTENDS83)
                    ;
                    adaptor.addChild(root_0, EXTENDS83_tree);
                    }

                    pushFollow(FOLLOW_typeList_in_normalInterfaceDeclaration1145);
                    typeList84=typeList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeList84.getTree());

                    }
                    break;

            }


            pushFollow(FOLLOW_interfaceBody_in_normalInterfaceDeclaration1149);
            interfaceBody85=interfaceBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceBody85.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 20, normalInterfaceDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "normalInterfaceDeclaration"


    public static class typeList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeList"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:329:1: typeList : type ( ',' type )* ;
    public final JavaParser.typeList_return typeList() throws RecognitionException {
        JavaParser.typeList_return retval = new JavaParser.typeList_return();
        retval.start = input.LT(1);

        int typeList_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal87=null;
        JavaParser.type_return type86 =null;

        JavaParser.type_return type88 =null;


        CommonTree char_literal87_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:330:5: ( type ( ',' type )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:330:9: type ( ',' type )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_type_in_typeList1172);
            type86=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type86.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:330:14: ( ',' type )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==68) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:330:15: ',' type
            	    {
            	    char_literal87=(Token)match(input,68,FOLLOW_68_in_typeList1175); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal87_tree = 
            	    (CommonTree)adaptor.create(char_literal87)
            	    ;
            	    adaptor.addChild(root_0, char_literal87_tree);
            	    }

            	    pushFollow(FOLLOW_type_in_typeList1177);
            	    type88=type();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, type88.getTree());

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 21, typeList_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "typeList"


    public static class classBody_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "classBody"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:333:1: classBody : '{' ( classBodyDeclaration )* '}' -> ^( CLASS_BODY ( classBodyDeclaration )* ) ;
    public final JavaParser.classBody_return classBody() throws RecognitionException {
        JavaParser.classBody_return retval = new JavaParser.classBody_return();
        retval.start = input.LT(1);

        int classBody_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal89=null;
        Token char_literal91=null;
        JavaParser.classBodyDeclaration_return classBodyDeclaration90 =null;


        CommonTree char_literal89_tree=null;
        CommonTree char_literal91_tree=null;
        RewriteRuleTokenStream stream_128=new RewriteRuleTokenStream(adaptor,"token 128");
        RewriteRuleTokenStream stream_124=new RewriteRuleTokenStream(adaptor,"token 124");
        RewriteRuleSubtreeStream stream_classBodyDeclaration=new RewriteRuleSubtreeStream(adaptor,"rule classBodyDeclaration");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:334:5: ( '{' ( classBodyDeclaration )* '}' -> ^( CLASS_BODY ( classBodyDeclaration )* ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:334:9: '{' ( classBodyDeclaration )* '}'
            {
            char_literal89=(Token)match(input,124,FOLLOW_124_in_classBody1202); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_124.add(char_literal89);


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:334:13: ( classBodyDeclaration )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==CLASS||LA37_0==ENUM||LA37_0==Identifier||LA37_0==LS||LA37_0==SEMI||LA37_0==STATIC||LA37_0==VOID||LA37_0==78||(LA37_0 >= 83 && LA37_0 <= 84)||LA37_0==86||LA37_0==89||LA37_0==93||LA37_0==96||LA37_0==98||(LA37_0 >= 102 && LA37_0 <= 105)||(LA37_0 >= 108 && LA37_0 <= 110)||(LA37_0 >= 112 && LA37_0 <= 113)||LA37_0==115||LA37_0==119||LA37_0==122||LA37_0==124) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:334:13: classBodyDeclaration
            	    {
            	    pushFollow(FOLLOW_classBodyDeclaration_in_classBody1204);
            	    classBodyDeclaration90=classBodyDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_classBodyDeclaration.add(classBodyDeclaration90.getTree());

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            char_literal91=(Token)match(input,128,FOLLOW_128_in_classBody1207); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_128.add(char_literal91);


            // AST REWRITE
            // elements: classBodyDeclaration
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 334:39: -> ^( CLASS_BODY ( classBodyDeclaration )* )
            {
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:334:42: ^( CLASS_BODY ( classBodyDeclaration )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(CLASS_BODY, "CLASS_BODY")
                , root_1);

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:334:55: ( classBodyDeclaration )*
                while ( stream_classBodyDeclaration.hasNext() ) {
                    adaptor.addChild(root_1, stream_classBodyDeclaration.nextTree());

                }
                stream_classBodyDeclaration.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 22, classBody_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "classBody"


    public static class interfaceBody_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interfaceBody"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:337:1: interfaceBody : '{' ( interfaceBodyDeclaration )* '}' ;
    public final JavaParser.interfaceBody_return interfaceBody() throws RecognitionException {
        JavaParser.interfaceBody_return retval = new JavaParser.interfaceBody_return();
        retval.start = input.LT(1);

        int interfaceBody_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal92=null;
        Token char_literal94=null;
        JavaParser.interfaceBodyDeclaration_return interfaceBodyDeclaration93 =null;


        CommonTree char_literal92_tree=null;
        CommonTree char_literal94_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:338:5: ( '{' ( interfaceBodyDeclaration )* '}' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:338:9: '{' ( interfaceBodyDeclaration )* '}'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal92=(Token)match(input,124,FOLLOW_124_in_interfaceBody1239); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal92_tree = 
            (CommonTree)adaptor.create(char_literal92)
            ;
            adaptor.addChild(root_0, char_literal92_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:338:13: ( interfaceBodyDeclaration )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==CLASS||LA38_0==ENUM||LA38_0==Identifier||LA38_0==LS||LA38_0==SEMI||LA38_0==STATIC||LA38_0==VOID||LA38_0==78||(LA38_0 >= 83 && LA38_0 <= 84)||LA38_0==86||LA38_0==89||LA38_0==93||LA38_0==96||LA38_0==98||(LA38_0 >= 102 && LA38_0 <= 105)||(LA38_0 >= 108 && LA38_0 <= 110)||(LA38_0 >= 112 && LA38_0 <= 113)||LA38_0==115||LA38_0==119||LA38_0==122) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:338:13: interfaceBodyDeclaration
            	    {
            	    pushFollow(FOLLOW_interfaceBodyDeclaration_in_interfaceBody1241);
            	    interfaceBodyDeclaration93=interfaceBodyDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceBodyDeclaration93.getTree());

            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);


            char_literal94=(Token)match(input,128,FOLLOW_128_in_interfaceBody1244); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal94_tree = 
            (CommonTree)adaptor.create(char_literal94)
            ;
            adaptor.addChild(root_0, char_literal94_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 23, interfaceBody_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "interfaceBody"


    public static class classBodyDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "classBodyDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:341:1: classBodyDeclaration : ( ';' | ( 'static' )? block | modifiers ! memberDecl[$modifiers.tree] );
    public final JavaParser.classBodyDeclaration_return classBodyDeclaration() throws RecognitionException {
        JavaParser.classBodyDeclaration_return retval = new JavaParser.classBodyDeclaration_return();
        retval.start = input.LT(1);

        int classBodyDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal95=null;
        Token string_literal96=null;
        JavaParser.block_return block97 =null;

        JavaParser.modifiers_return modifiers98 =null;

        JavaParser.memberDecl_return memberDecl99 =null;


        CommonTree char_literal95_tree=null;
        CommonTree string_literal96_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:342:5: ( ';' | ( 'static' )? block | modifiers ! memberDecl[$modifiers.tree] )
            int alt40=3;
            switch ( input.LA(1) ) {
            case SEMI:
                {
                alt40=1;
                }
                break;
            case STATIC:
                {
                int LA40_2 = input.LA(2);

                if ( (LA40_2==124) ) {
                    alt40=2;
                }
                else if ( (LA40_2==CLASS||LA40_2==ENUM||LA40_2==Identifier||LA40_2==LS||LA40_2==STATIC||LA40_2==VOID||LA40_2==78||(LA40_2 >= 83 && LA40_2 <= 84)||LA40_2==86||LA40_2==89||LA40_2==93||LA40_2==96||LA40_2==98||(LA40_2 >= 102 && LA40_2 <= 105)||(LA40_2 >= 108 && LA40_2 <= 110)||(LA40_2 >= 112 && LA40_2 <= 113)||LA40_2==115||LA40_2==119||LA40_2==122) ) {
                    alt40=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 40, 2, input);

                    throw nvae;

                }
                }
                break;
            case 124:
                {
                alt40=2;
                }
                break;
            case CLASS:
            case ENUM:
            case Identifier:
            case LS:
            case VOID:
            case 78:
            case 83:
            case 84:
            case 86:
            case 89:
            case 93:
            case 96:
            case 98:
            case 102:
            case 103:
            case 104:
            case 105:
            case 108:
            case 109:
            case 110:
            case 112:
            case 113:
            case 115:
            case 119:
            case 122:
                {
                alt40=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;

            }

            switch (alt40) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:342:9: ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal95=(Token)match(input,SEMI,FOLLOW_SEMI_in_classBodyDeclaration1263); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal95_tree = 
                    (CommonTree)adaptor.create(char_literal95)
                    ;
                    adaptor.addChild(root_0, char_literal95_tree);
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:343:9: ( 'static' )? block
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:343:9: ( 'static' )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==STATIC) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:343:9: 'static'
                            {
                            string_literal96=(Token)match(input,STATIC,FOLLOW_STATIC_in_classBodyDeclaration1273); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal96_tree = 
                            (CommonTree)adaptor.create(string_literal96)
                            ;
                            adaptor.addChild(root_0, string_literal96_tree);
                            }

                            }
                            break;

                    }


                    pushFollow(FOLLOW_block_in_classBodyDeclaration1276);
                    block97=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block97.getTree());

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:344:9: modifiers ! memberDecl[$modifiers.tree]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_modifiers_in_classBodyDeclaration1286);
                    modifiers98=modifiers();

                    state._fsp--;
                    if (state.failed) return retval;

                    pushFollow(FOLLOW_memberDecl_in_classBodyDeclaration1289);
                    memberDecl99=memberDecl((modifiers98!=null?((CommonTree)modifiers98.tree):null));

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, memberDecl99.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 24, classBodyDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "classBodyDeclaration"


    public static class memberDecl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "memberDecl"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:347:1: memberDecl[CommonTree modifiers] : ( genericMethodOrConstructorDecl[$modifiers] | memberDeclaration[$modifiers] | VOID Identifier voidMethodDeclaratorRest -> ^( METHOD VOID Identifier voidMethodDeclaratorRest ) | Identifier constructorDeclaratorRest -> ^( CONSTRUCTOR Identifier constructorDeclaratorRest ) | interfaceDeclaration[$modifiers] | classDeclaration[$modifiers] );
    public final JavaParser.memberDecl_return memberDecl(CommonTree modifiers) throws RecognitionException {
        JavaParser.memberDecl_return retval = new JavaParser.memberDecl_return();
        retval.start = input.LT(1);

        int memberDecl_StartIndex = input.index();

        CommonTree root_0 = null;

        Token VOID102=null;
        Token Identifier103=null;
        Token Identifier105=null;
        JavaParser.genericMethodOrConstructorDecl_return genericMethodOrConstructorDecl100 =null;

        JavaParser.memberDeclaration_return memberDeclaration101 =null;

        JavaParser.voidMethodDeclaratorRest_return voidMethodDeclaratorRest104 =null;

        JavaParser.constructorDeclaratorRest_return constructorDeclaratorRest106 =null;

        JavaParser.interfaceDeclaration_return interfaceDeclaration107 =null;

        JavaParser.classDeclaration_return classDeclaration108 =null;


        CommonTree VOID102_tree=null;
        CommonTree Identifier103_tree=null;
        CommonTree Identifier105_tree=null;
        RewriteRuleTokenStream stream_VOID=new RewriteRuleTokenStream(adaptor,"token VOID");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_constructorDeclaratorRest=new RewriteRuleSubtreeStream(adaptor,"rule constructorDeclaratorRest");
        RewriteRuleSubtreeStream stream_voidMethodDeclaratorRest=new RewriteRuleSubtreeStream(adaptor,"rule voidMethodDeclaratorRest");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:348:5: ( genericMethodOrConstructorDecl[$modifiers] | memberDeclaration[$modifiers] | VOID Identifier voidMethodDeclaratorRest -> ^( METHOD VOID Identifier voidMethodDeclaratorRest ) | Identifier constructorDeclaratorRest -> ^( CONSTRUCTOR Identifier constructorDeclaratorRest ) | interfaceDeclaration[$modifiers] | classDeclaration[$modifiers] )
            int alt41=6;
            switch ( input.LA(1) ) {
            case LS:
                {
                alt41=1;
                }
                break;
            case Identifier:
                {
                int LA41_2 = input.LA(2);

                if ( (LA41_2==DOT||LA41_2==Identifier||LA41_2==79) ) {
                    alt41=2;
                }
                else if ( (LA41_2==62) ) {
                    alt41=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 41, 2, input);

                    throw nvae;

                }
                }
                break;
            case 84:
            case 86:
            case 89:
            case 93:
            case 98:
            case 102:
            case 104:
            case 112:
                {
                alt41=2;
                }
                break;
            case VOID:
                {
                alt41=3;
                }
                break;
            case 78:
            case 103:
                {
                alt41=5;
                }
                break;
            case CLASS:
            case ENUM:
                {
                alt41=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }

            switch (alt41) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:348:9: genericMethodOrConstructorDecl[$modifiers]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_genericMethodOrConstructorDecl_in_memberDecl1314);
                    genericMethodOrConstructorDecl100=genericMethodOrConstructorDecl(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, genericMethodOrConstructorDecl100.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:349:9: memberDeclaration[$modifiers]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_memberDeclaration_in_memberDecl1325);
                    memberDeclaration101=memberDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, memberDeclaration101.getTree());

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:350:9: VOID Identifier voidMethodDeclaratorRest
                    {
                    VOID102=(Token)match(input,VOID,FOLLOW_VOID_in_memberDecl1336); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VOID.add(VOID102);


                    Identifier103=(Token)match(input,Identifier,FOLLOW_Identifier_in_memberDecl1338); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier103);


                    pushFollow(FOLLOW_voidMethodDeclaratorRest_in_memberDecl1340);
                    voidMethodDeclaratorRest104=voidMethodDeclaratorRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_voidMethodDeclaratorRest.add(voidMethodDeclaratorRest104.getTree());

                    // AST REWRITE
                    // elements: Identifier, voidMethodDeclaratorRest, VOID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 350:50: -> ^( METHOD VOID Identifier voidMethodDeclaratorRest )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:350:53: ^( METHOD VOID Identifier voidMethodDeclaratorRest )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(METHOD, "METHOD")
                        , root_1);

                        adaptor.addChild(root_1, modifiers);

                        adaptor.addChild(root_1, 
                        stream_VOID.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_Identifier.nextNode()
                        );

                        adaptor.addChild(root_1, stream_voidMethodDeclaratorRest.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:351:9: Identifier constructorDeclaratorRest
                    {
                    Identifier105=(Token)match(input,Identifier,FOLLOW_Identifier_in_memberDecl1364); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier105);


                    pushFollow(FOLLOW_constructorDeclaratorRest_in_memberDecl1366);
                    constructorDeclaratorRest106=constructorDeclaratorRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_constructorDeclaratorRest.add(constructorDeclaratorRest106.getTree());

                    // AST REWRITE
                    // elements: constructorDeclaratorRest, Identifier
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 351:46: -> ^( CONSTRUCTOR Identifier constructorDeclaratorRest )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:351:49: ^( CONSTRUCTOR Identifier constructorDeclaratorRest )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(CONSTRUCTOR, "CONSTRUCTOR")
                        , root_1);

                        adaptor.addChild(root_1, modifiers);

                        adaptor.addChild(root_1, 
                        stream_Identifier.nextNode()
                        );

                        adaptor.addChild(root_1, stream_constructorDeclaratorRest.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:352:9: interfaceDeclaration[$modifiers]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_interfaceDeclaration_in_memberDecl1388);
                    interfaceDeclaration107=interfaceDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceDeclaration107.getTree());

                    }
                    break;
                case 6 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:353:9: classDeclaration[$modifiers]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_classDeclaration_in_memberDecl1399);
                    classDeclaration108=classDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classDeclaration108.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 25, memberDecl_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "memberDecl"


    public static class memberDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "memberDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:356:1: memberDeclaration[CommonTree modifiers] : ( type methodDeclaration -> ^( METHOD type methodDeclaration ) | type fieldDeclaration -> ^( MEMBER type fieldDeclaration ) );
    public final JavaParser.memberDeclaration_return memberDeclaration(CommonTree modifiers) throws RecognitionException {
        JavaParser.memberDeclaration_return retval = new JavaParser.memberDeclaration_return();
        retval.start = input.LT(1);

        int memberDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.type_return type109 =null;

        JavaParser.methodDeclaration_return methodDeclaration110 =null;

        JavaParser.type_return type111 =null;

        JavaParser.fieldDeclaration_return fieldDeclaration112 =null;


        RewriteRuleSubtreeStream stream_fieldDeclaration=new RewriteRuleSubtreeStream(adaptor,"rule fieldDeclaration");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        RewriteRuleSubtreeStream stream_methodDeclaration=new RewriteRuleSubtreeStream(adaptor,"rule methodDeclaration");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:357:5: ( type methodDeclaration -> ^( METHOD type methodDeclaration ) | type fieldDeclaration -> ^( MEMBER type fieldDeclaration ) )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==Identifier) ) {
                int LA42_1 = input.LA(2);

                if ( (synpred53_Java()) ) {
                    alt42=1;
                }
                else if ( (true) ) {
                    alt42=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 42, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA42_0==84||LA42_0==86||LA42_0==89||LA42_0==93||LA42_0==98||LA42_0==102||LA42_0==104||LA42_0==112) ) {
                int LA42_2 = input.LA(2);

                if ( (synpred53_Java()) ) {
                    alt42=1;
                }
                else if ( (true) ) {
                    alt42=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 42, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }
            switch (alt42) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:357:9: type methodDeclaration
                    {
                    pushFollow(FOLLOW_type_in_memberDeclaration1424);
                    type109=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_type.add(type109.getTree());

                    pushFollow(FOLLOW_methodDeclaration_in_memberDeclaration1426);
                    methodDeclaration110=methodDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_methodDeclaration.add(methodDeclaration110.getTree());

                    // AST REWRITE
                    // elements: type, methodDeclaration
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 357:32: -> ^( METHOD type methodDeclaration )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:357:35: ^( METHOD type methodDeclaration )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(METHOD, "METHOD")
                        , root_1);

                        adaptor.addChild(root_1, modifiers);

                        adaptor.addChild(root_1, stream_type.nextTree());

                        adaptor.addChild(root_1, stream_methodDeclaration.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:358:9: type fieldDeclaration
                    {
                    pushFollow(FOLLOW_type_in_memberDeclaration1449);
                    type111=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_type.add(type111.getTree());

                    pushFollow(FOLLOW_fieldDeclaration_in_memberDeclaration1451);
                    fieldDeclaration112=fieldDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fieldDeclaration.add(fieldDeclaration112.getTree());

                    // AST REWRITE
                    // elements: fieldDeclaration, type
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 358:31: -> ^( MEMBER type fieldDeclaration )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:358:34: ^( MEMBER type fieldDeclaration )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(MEMBER, "MEMBER")
                        , root_1);

                        adaptor.addChild(root_1, modifiers);

                        adaptor.addChild(root_1, stream_type.nextTree());

                        adaptor.addChild(root_1, stream_fieldDeclaration.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 26, memberDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "memberDeclaration"


    public static class genericMethodOrConstructorDecl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "genericMethodOrConstructorDecl"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:361:1: genericMethodOrConstructorDecl[CommonTree modifiers] : typeParameters ! genericMethodOrConstructorRest[$modifiers, $typeParameters.tree] ;
    public final JavaParser.genericMethodOrConstructorDecl_return genericMethodOrConstructorDecl(CommonTree modifiers) throws RecognitionException {
        JavaParser.genericMethodOrConstructorDecl_return retval = new JavaParser.genericMethodOrConstructorDecl_return();
        retval.start = input.LT(1);

        int genericMethodOrConstructorDecl_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.typeParameters_return typeParameters113 =null;

        JavaParser.genericMethodOrConstructorRest_return genericMethodOrConstructorRest114 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:362:5: ( typeParameters ! genericMethodOrConstructorRest[$modifiers, $typeParameters.tree] )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:362:9: typeParameters ! genericMethodOrConstructorRest[$modifiers, $typeParameters.tree]
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_typeParameters_in_genericMethodOrConstructorDecl1483);
            typeParameters113=typeParameters();

            state._fsp--;
            if (state.failed) return retval;

            pushFollow(FOLLOW_genericMethodOrConstructorRest_in_genericMethodOrConstructorDecl1486);
            genericMethodOrConstructorRest114=genericMethodOrConstructorRest(modifiers, (typeParameters113!=null?((CommonTree)typeParameters113.tree):null));

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, genericMethodOrConstructorRest114.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 27, genericMethodOrConstructorDecl_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "genericMethodOrConstructorDecl"


    public static class genericMethodOrConstructorRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "genericMethodOrConstructorRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:365:1: genericMethodOrConstructorRest[CommonTree modifiers, CommonTree typeParameters] : ( VOID Identifier methodDeclaratorRest -> ^( METHOD VOID Identifier methodDeclaratorRest ) | type Identifier methodDeclaratorRest -> ^( METHOD type Identifier methodDeclaratorRest ) | Identifier constructorDeclaratorRest -> ^( CONSTRUCTOR Identifier constructorDeclaratorRest ) );
    public final JavaParser.genericMethodOrConstructorRest_return genericMethodOrConstructorRest(CommonTree modifiers, CommonTree typeParameters) throws RecognitionException {
        JavaParser.genericMethodOrConstructorRest_return retval = new JavaParser.genericMethodOrConstructorRest_return();
        retval.start = input.LT(1);

        int genericMethodOrConstructorRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token VOID115=null;
        Token Identifier116=null;
        Token Identifier119=null;
        Token Identifier121=null;
        JavaParser.methodDeclaratorRest_return methodDeclaratorRest117 =null;

        JavaParser.type_return type118 =null;

        JavaParser.methodDeclaratorRest_return methodDeclaratorRest120 =null;

        JavaParser.constructorDeclaratorRest_return constructorDeclaratorRest122 =null;


        CommonTree VOID115_tree=null;
        CommonTree Identifier116_tree=null;
        CommonTree Identifier119_tree=null;
        CommonTree Identifier121_tree=null;
        RewriteRuleTokenStream stream_VOID=new RewriteRuleTokenStream(adaptor,"token VOID");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_constructorDeclaratorRest=new RewriteRuleSubtreeStream(adaptor,"rule constructorDeclaratorRest");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        RewriteRuleSubtreeStream stream_methodDeclaratorRest=new RewriteRuleSubtreeStream(adaptor,"rule methodDeclaratorRest");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:366:5: ( VOID Identifier methodDeclaratorRest -> ^( METHOD VOID Identifier methodDeclaratorRest ) | type Identifier methodDeclaratorRest -> ^( METHOD type Identifier methodDeclaratorRest ) | Identifier constructorDeclaratorRest -> ^( CONSTRUCTOR Identifier constructorDeclaratorRest ) )
            int alt43=3;
            switch ( input.LA(1) ) {
            case VOID:
                {
                alt43=1;
                }
                break;
            case Identifier:
                {
                int LA43_2 = input.LA(2);

                if ( (LA43_2==DOT||LA43_2==Identifier||LA43_2==79) ) {
                    alt43=2;
                }
                else if ( (LA43_2==62) ) {
                    alt43=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 43, 2, input);

                    throw nvae;

                }
                }
                break;
            case 84:
            case 86:
            case 89:
            case 93:
            case 98:
            case 102:
            case 104:
            case 112:
                {
                alt43=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;

            }

            switch (alt43) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:366:9: VOID Identifier methodDeclaratorRest
                    {
                    VOID115=(Token)match(input,VOID,FOLLOW_VOID_in_genericMethodOrConstructorRest1511); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VOID.add(VOID115);


                    Identifier116=(Token)match(input,Identifier,FOLLOW_Identifier_in_genericMethodOrConstructorRest1513); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier116);


                    pushFollow(FOLLOW_methodDeclaratorRest_in_genericMethodOrConstructorRest1515);
                    methodDeclaratorRest117=methodDeclaratorRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_methodDeclaratorRest.add(methodDeclaratorRest117.getTree());

                    // AST REWRITE
                    // elements: VOID, methodDeclaratorRest, Identifier
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 366:46: -> ^( METHOD VOID Identifier methodDeclaratorRest )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:366:49: ^( METHOD VOID Identifier methodDeclaratorRest )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(METHOD, "METHOD")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_VOID.nextNode()
                        );

                        adaptor.addChild(root_1, 
                        stream_Identifier.nextNode()
                        );

                        adaptor.addChild(root_1, modifiers);

                        adaptor.addChild(root_1, typeParameters);

                        adaptor.addChild(root_1, stream_methodDeclaratorRest.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:367:9: type Identifier methodDeclaratorRest
                    {
                    pushFollow(FOLLOW_type_in_genericMethodOrConstructorRest1542);
                    type118=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_type.add(type118.getTree());

                    Identifier119=(Token)match(input,Identifier,FOLLOW_Identifier_in_genericMethodOrConstructorRest1544); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier119);


                    pushFollow(FOLLOW_methodDeclaratorRest_in_genericMethodOrConstructorRest1546);
                    methodDeclaratorRest120=methodDeclaratorRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_methodDeclaratorRest.add(methodDeclaratorRest120.getTree());

                    // AST REWRITE
                    // elements: methodDeclaratorRest, Identifier, type
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 367:46: -> ^( METHOD type Identifier methodDeclaratorRest )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:367:49: ^( METHOD type Identifier methodDeclaratorRest )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(METHOD, "METHOD")
                        , root_1);

                        adaptor.addChild(root_1, stream_type.nextTree());

                        adaptor.addChild(root_1, 
                        stream_Identifier.nextNode()
                        );

                        adaptor.addChild(root_1, modifiers);

                        adaptor.addChild(root_1, typeParameters);

                        adaptor.addChild(root_1, stream_methodDeclaratorRest.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:368:9: Identifier constructorDeclaratorRest
                    {
                    Identifier121=(Token)match(input,Identifier,FOLLOW_Identifier_in_genericMethodOrConstructorRest1573); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier121);


                    pushFollow(FOLLOW_constructorDeclaratorRest_in_genericMethodOrConstructorRest1575);
                    constructorDeclaratorRest122=constructorDeclaratorRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_constructorDeclaratorRest.add(constructorDeclaratorRest122.getTree());

                    // AST REWRITE
                    // elements: constructorDeclaratorRest, Identifier
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 368:46: -> ^( CONSTRUCTOR Identifier constructorDeclaratorRest )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:368:49: ^( CONSTRUCTOR Identifier constructorDeclaratorRest )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(CONSTRUCTOR, "CONSTRUCTOR")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_Identifier.nextNode()
                        );

                        adaptor.addChild(root_1, modifiers);

                        adaptor.addChild(root_1, typeParameters);

                        adaptor.addChild(root_1, stream_constructorDeclaratorRest.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 28, genericMethodOrConstructorRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "genericMethodOrConstructorRest"


    public static class methodDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "methodDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:371:1: methodDeclaration : Identifier methodDeclaratorRest ;
    public final JavaParser.methodDeclaration_return methodDeclaration() throws RecognitionException {
        JavaParser.methodDeclaration_return retval = new JavaParser.methodDeclaration_return();
        retval.start = input.LT(1);

        int methodDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier123=null;
        JavaParser.methodDeclaratorRest_return methodDeclaratorRest124 =null;


        CommonTree Identifier123_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:372:5: ( Identifier methodDeclaratorRest )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:372:9: Identifier methodDeclaratorRest
            {
            root_0 = (CommonTree)adaptor.nil();


            Identifier123=(Token)match(input,Identifier,FOLLOW_Identifier_in_methodDeclaration1609); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier123_tree = 
            (CommonTree)adaptor.create(Identifier123)
            ;
            adaptor.addChild(root_0, Identifier123_tree);
            }

            pushFollow(FOLLOW_methodDeclaratorRest_in_methodDeclaration1611);
            methodDeclaratorRest124=methodDeclaratorRest();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, methodDeclaratorRest124.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 29, methodDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "methodDeclaration"


    public static class fieldDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fieldDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:375:1: fieldDeclaration : variableDeclarators ';' ;
    public final JavaParser.fieldDeclaration_return fieldDeclaration() throws RecognitionException {
        JavaParser.fieldDeclaration_return retval = new JavaParser.fieldDeclaration_return();
        retval.start = input.LT(1);

        int fieldDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal126=null;
        JavaParser.variableDeclarators_return variableDeclarators125 =null;


        CommonTree char_literal126_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:376:5: ( variableDeclarators ';' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:376:9: variableDeclarators ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_variableDeclarators_in_fieldDeclaration1630);
            variableDeclarators125=variableDeclarators();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarators125.getTree());

            char_literal126=(Token)match(input,SEMI,FOLLOW_SEMI_in_fieldDeclaration1632); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal126_tree = 
            (CommonTree)adaptor.create(char_literal126)
            ;
            adaptor.addChild(root_0, char_literal126_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 30, fieldDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "fieldDeclaration"


    public static class interfaceBodyDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interfaceBodyDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:379:1: interfaceBodyDeclaration : ( modifiers interfaceMemberDecl[$modifiers.tree] | ';' );
    public final JavaParser.interfaceBodyDeclaration_return interfaceBodyDeclaration() throws RecognitionException {
        JavaParser.interfaceBodyDeclaration_return retval = new JavaParser.interfaceBodyDeclaration_return();
        retval.start = input.LT(1);

        int interfaceBodyDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal129=null;
        JavaParser.modifiers_return modifiers127 =null;

        JavaParser.interfaceMemberDecl_return interfaceMemberDecl128 =null;


        CommonTree char_literal129_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:380:5: ( modifiers interfaceMemberDecl[$modifiers.tree] | ';' )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==CLASS||LA44_0==ENUM||LA44_0==Identifier||LA44_0==LS||LA44_0==STATIC||LA44_0==VOID||LA44_0==78||(LA44_0 >= 83 && LA44_0 <= 84)||LA44_0==86||LA44_0==89||LA44_0==93||LA44_0==96||LA44_0==98||(LA44_0 >= 102 && LA44_0 <= 105)||(LA44_0 >= 108 && LA44_0 <= 110)||(LA44_0 >= 112 && LA44_0 <= 113)||LA44_0==115||LA44_0==119||LA44_0==122) ) {
                alt44=1;
            }
            else if ( (LA44_0==SEMI) ) {
                alt44=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;

            }
            switch (alt44) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:380:9: modifiers interfaceMemberDecl[$modifiers.tree]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_modifiers_in_interfaceBodyDeclaration1659);
                    modifiers127=modifiers();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers127.getTree());

                    pushFollow(FOLLOW_interfaceMemberDecl_in_interfaceBodyDeclaration1661);
                    interfaceMemberDecl128=interfaceMemberDecl((modifiers127!=null?((CommonTree)modifiers127.tree):null));

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceMemberDecl128.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:381:9: ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal129=(Token)match(input,SEMI,FOLLOW_SEMI_in_interfaceBodyDeclaration1672); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal129_tree = 
                    (CommonTree)adaptor.create(char_literal129)
                    ;
                    adaptor.addChild(root_0, char_literal129_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 31, interfaceBodyDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "interfaceBodyDeclaration"


    public static class interfaceMemberDecl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interfaceMemberDecl"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:384:1: interfaceMemberDecl[CommonTree modifiers] : ( interfaceMethodOrFieldDecl | interfaceGenericMethodDecl | 'void' Identifier voidInterfaceMethodDeclaratorRest | interfaceDeclaration[$modifiers] | classDeclaration[$modifiers] );
    public final JavaParser.interfaceMemberDecl_return interfaceMemberDecl(CommonTree modifiers) throws RecognitionException {
        JavaParser.interfaceMemberDecl_return retval = new JavaParser.interfaceMemberDecl_return();
        retval.start = input.LT(1);

        int interfaceMemberDecl_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal132=null;
        Token Identifier133=null;
        JavaParser.interfaceMethodOrFieldDecl_return interfaceMethodOrFieldDecl130 =null;

        JavaParser.interfaceGenericMethodDecl_return interfaceGenericMethodDecl131 =null;

        JavaParser.voidInterfaceMethodDeclaratorRest_return voidInterfaceMethodDeclaratorRest134 =null;

        JavaParser.interfaceDeclaration_return interfaceDeclaration135 =null;

        JavaParser.classDeclaration_return classDeclaration136 =null;


        CommonTree string_literal132_tree=null;
        CommonTree Identifier133_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:385:5: ( interfaceMethodOrFieldDecl | interfaceGenericMethodDecl | 'void' Identifier voidInterfaceMethodDeclaratorRest | interfaceDeclaration[$modifiers] | classDeclaration[$modifiers] )
            int alt45=5;
            switch ( input.LA(1) ) {
            case Identifier:
            case 84:
            case 86:
            case 89:
            case 93:
            case 98:
            case 102:
            case 104:
            case 112:
                {
                alt45=1;
                }
                break;
            case LS:
                {
                alt45=2;
                }
                break;
            case VOID:
                {
                alt45=3;
                }
                break;
            case 78:
            case 103:
                {
                alt45=4;
                }
                break;
            case CLASS:
            case ENUM:
                {
                alt45=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;

            }

            switch (alt45) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:385:9: interfaceMethodOrFieldDecl
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_interfaceMethodOrFieldDecl_in_interfaceMemberDecl1692);
                    interfaceMethodOrFieldDecl130=interfaceMethodOrFieldDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceMethodOrFieldDecl130.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:386:9: interfaceGenericMethodDecl
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_interfaceGenericMethodDecl_in_interfaceMemberDecl1702);
                    interfaceGenericMethodDecl131=interfaceGenericMethodDecl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceGenericMethodDecl131.getTree());

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:387:9: 'void' Identifier voidInterfaceMethodDeclaratorRest
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal132=(Token)match(input,VOID,FOLLOW_VOID_in_interfaceMemberDecl1712); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal132_tree = 
                    (CommonTree)adaptor.create(string_literal132)
                    ;
                    adaptor.addChild(root_0, string_literal132_tree);
                    }

                    Identifier133=(Token)match(input,Identifier,FOLLOW_Identifier_in_interfaceMemberDecl1714); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Identifier133_tree = 
                    (CommonTree)adaptor.create(Identifier133)
                    ;
                    adaptor.addChild(root_0, Identifier133_tree);
                    }

                    pushFollow(FOLLOW_voidInterfaceMethodDeclaratorRest_in_interfaceMemberDecl1716);
                    voidInterfaceMethodDeclaratorRest134=voidInterfaceMethodDeclaratorRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, voidInterfaceMethodDeclaratorRest134.getTree());

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:388:9: interfaceDeclaration[$modifiers]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_interfaceDeclaration_in_interfaceMemberDecl1726);
                    interfaceDeclaration135=interfaceDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceDeclaration135.getTree());

                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:389:9: classDeclaration[$modifiers]
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_classDeclaration_in_interfaceMemberDecl1737);
                    classDeclaration136=classDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classDeclaration136.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 32, interfaceMemberDecl_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "interfaceMemberDecl"


    public static class interfaceMethodOrFieldDecl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interfaceMethodOrFieldDecl"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:392:1: interfaceMethodOrFieldDecl : type Identifier interfaceMethodOrFieldRest ;
    public final JavaParser.interfaceMethodOrFieldDecl_return interfaceMethodOrFieldDecl() throws RecognitionException {
        JavaParser.interfaceMethodOrFieldDecl_return retval = new JavaParser.interfaceMethodOrFieldDecl_return();
        retval.start = input.LT(1);

        int interfaceMethodOrFieldDecl_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier138=null;
        JavaParser.type_return type137 =null;

        JavaParser.interfaceMethodOrFieldRest_return interfaceMethodOrFieldRest139 =null;


        CommonTree Identifier138_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:393:5: ( type Identifier interfaceMethodOrFieldRest )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:393:9: type Identifier interfaceMethodOrFieldRest
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_type_in_interfaceMethodOrFieldDecl1761);
            type137=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type137.getTree());

            Identifier138=(Token)match(input,Identifier,FOLLOW_Identifier_in_interfaceMethodOrFieldDecl1763); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier138_tree = 
            (CommonTree)adaptor.create(Identifier138)
            ;
            adaptor.addChild(root_0, Identifier138_tree);
            }

            pushFollow(FOLLOW_interfaceMethodOrFieldRest_in_interfaceMethodOrFieldDecl1765);
            interfaceMethodOrFieldRest139=interfaceMethodOrFieldRest();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceMethodOrFieldRest139.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 33, interfaceMethodOrFieldDecl_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "interfaceMethodOrFieldDecl"


    public static class interfaceMethodOrFieldRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interfaceMethodOrFieldRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:396:1: interfaceMethodOrFieldRest : ( constantDeclaratorsRest ';' | interfaceMethodDeclaratorRest );
    public final JavaParser.interfaceMethodOrFieldRest_return interfaceMethodOrFieldRest() throws RecognitionException {
        JavaParser.interfaceMethodOrFieldRest_return retval = new JavaParser.interfaceMethodOrFieldRest_return();
        retval.start = input.LT(1);

        int interfaceMethodOrFieldRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal141=null;
        JavaParser.constantDeclaratorsRest_return constantDeclaratorsRest140 =null;

        JavaParser.interfaceMethodDeclaratorRest_return interfaceMethodDeclaratorRest142 =null;


        CommonTree char_literal141_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:397:5: ( constantDeclaratorsRest ';' | interfaceMethodDeclaratorRest )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==EQ||LA46_0==79) ) {
                alt46=1;
            }
            else if ( (LA46_0==62) ) {
                alt46=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }
            switch (alt46) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:397:9: constantDeclaratorsRest ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_constantDeclaratorsRest_in_interfaceMethodOrFieldRest1788);
                    constantDeclaratorsRest140=constantDeclaratorsRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constantDeclaratorsRest140.getTree());

                    char_literal141=(Token)match(input,SEMI,FOLLOW_SEMI_in_interfaceMethodOrFieldRest1790); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal141_tree = 
                    (CommonTree)adaptor.create(char_literal141)
                    ;
                    adaptor.addChild(root_0, char_literal141_tree);
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:398:9: interfaceMethodDeclaratorRest
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_interfaceMethodDeclaratorRest_in_interfaceMethodOrFieldRest1800);
                    interfaceMethodDeclaratorRest142=interfaceMethodDeclaratorRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceMethodDeclaratorRest142.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 34, interfaceMethodOrFieldRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "interfaceMethodOrFieldRest"


    public static class methodDeclaratorRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "methodDeclaratorRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:401:1: methodDeclaratorRest : formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ( methodBody | ';' ) ;
    public final JavaParser.methodDeclaratorRest_return methodDeclaratorRest() throws RecognitionException {
        JavaParser.methodDeclaratorRest_return retval = new JavaParser.methodDeclaratorRest_return();
        retval.start = input.LT(1);

        int methodDeclaratorRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal144=null;
        Token char_literal145=null;
        Token string_literal146=null;
        Token char_literal149=null;
        JavaParser.formalParameters_return formalParameters143 =null;

        JavaParser.qualifiedNameList_return qualifiedNameList147 =null;

        JavaParser.methodBody_return methodBody148 =null;


        CommonTree char_literal144_tree=null;
        CommonTree char_literal145_tree=null;
        CommonTree string_literal146_tree=null;
        CommonTree char_literal149_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:402:5: ( formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ( methodBody | ';' ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:402:9: formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ( methodBody | ';' )
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_formalParameters_in_methodDeclaratorRest1823);
            formalParameters143=formalParameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameters143.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:402:26: ( '[' ']' )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( (LA47_0==79) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:402:27: '[' ']'
            	    {
            	    char_literal144=(Token)match(input,79,FOLLOW_79_in_methodDeclaratorRest1826); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal144_tree = 
            	    (CommonTree)adaptor.create(char_literal144)
            	    ;
            	    adaptor.addChild(root_0, char_literal144_tree);
            	    }

            	    char_literal145=(Token)match(input,80,FOLLOW_80_in_methodDeclaratorRest1828); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal145_tree = 
            	    (CommonTree)adaptor.create(char_literal145)
            	    ;
            	    adaptor.addChild(root_0, char_literal145_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:403:9: ( 'throws' qualifiedNameList )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==118) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:403:10: 'throws' qualifiedNameList
                    {
                    string_literal146=(Token)match(input,118,FOLLOW_118_in_methodDeclaratorRest1841); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal146_tree = 
                    (CommonTree)adaptor.create(string_literal146)
                    ;
                    adaptor.addChild(root_0, string_literal146_tree);
                    }

                    pushFollow(FOLLOW_qualifiedNameList_in_methodDeclaratorRest1843);
                    qualifiedNameList147=qualifiedNameList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedNameList147.getTree());

                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:404:9: ( methodBody | ';' )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==124) ) {
                alt49=1;
            }
            else if ( (LA49_0==SEMI) ) {
                alt49=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;

            }
            switch (alt49) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:404:13: methodBody
                    {
                    pushFollow(FOLLOW_methodBody_in_methodDeclaratorRest1859);
                    methodBody148=methodBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, methodBody148.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:405:13: ';'
                    {
                    char_literal149=(Token)match(input,SEMI,FOLLOW_SEMI_in_methodDeclaratorRest1873); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal149_tree = 
                    (CommonTree)adaptor.create(char_literal149)
                    ;
                    adaptor.addChild(root_0, char_literal149_tree);
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 35, methodDeclaratorRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "methodDeclaratorRest"


    public static class voidMethodDeclaratorRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "voidMethodDeclaratorRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:409:1: voidMethodDeclaratorRest : formalParameters ( 'throws' qualifiedNameList )? ( methodBody | ';' ) ;
    public final JavaParser.voidMethodDeclaratorRest_return voidMethodDeclaratorRest() throws RecognitionException {
        JavaParser.voidMethodDeclaratorRest_return retval = new JavaParser.voidMethodDeclaratorRest_return();
        retval.start = input.LT(1);

        int voidMethodDeclaratorRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal151=null;
        Token char_literal154=null;
        JavaParser.formalParameters_return formalParameters150 =null;

        JavaParser.qualifiedNameList_return qualifiedNameList152 =null;

        JavaParser.methodBody_return methodBody153 =null;


        CommonTree string_literal151_tree=null;
        CommonTree char_literal154_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:410:5: ( formalParameters ( 'throws' qualifiedNameList )? ( methodBody | ';' ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:410:9: formalParameters ( 'throws' qualifiedNameList )? ( methodBody | ';' )
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_formalParameters_in_voidMethodDeclaratorRest1906);
            formalParameters150=formalParameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameters150.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:410:26: ( 'throws' qualifiedNameList )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==118) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:410:27: 'throws' qualifiedNameList
                    {
                    string_literal151=(Token)match(input,118,FOLLOW_118_in_voidMethodDeclaratorRest1909); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal151_tree = 
                    (CommonTree)adaptor.create(string_literal151)
                    ;
                    adaptor.addChild(root_0, string_literal151_tree);
                    }

                    pushFollow(FOLLOW_qualifiedNameList_in_voidMethodDeclaratorRest1911);
                    qualifiedNameList152=qualifiedNameList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedNameList152.getTree());

                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:411:9: ( methodBody | ';' )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==124) ) {
                alt51=1;
            }
            else if ( (LA51_0==SEMI) ) {
                alt51=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;

            }
            switch (alt51) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:411:13: methodBody
                    {
                    pushFollow(FOLLOW_methodBody_in_voidMethodDeclaratorRest1927);
                    methodBody153=methodBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, methodBody153.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:412:13: ';'
                    {
                    char_literal154=(Token)match(input,SEMI,FOLLOW_SEMI_in_voidMethodDeclaratorRest1941); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal154_tree = 
                    (CommonTree)adaptor.create(char_literal154)
                    ;
                    adaptor.addChild(root_0, char_literal154_tree);
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 36, voidMethodDeclaratorRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "voidMethodDeclaratorRest"


    public static class interfaceMethodDeclaratorRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interfaceMethodDeclaratorRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:416:1: interfaceMethodDeclaratorRest : formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ';' ;
    public final JavaParser.interfaceMethodDeclaratorRest_return interfaceMethodDeclaratorRest() throws RecognitionException {
        JavaParser.interfaceMethodDeclaratorRest_return retval = new JavaParser.interfaceMethodDeclaratorRest_return();
        retval.start = input.LT(1);

        int interfaceMethodDeclaratorRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal156=null;
        Token char_literal157=null;
        Token string_literal158=null;
        Token char_literal160=null;
        JavaParser.formalParameters_return formalParameters155 =null;

        JavaParser.qualifiedNameList_return qualifiedNameList159 =null;


        CommonTree char_literal156_tree=null;
        CommonTree char_literal157_tree=null;
        CommonTree string_literal158_tree=null;
        CommonTree char_literal160_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:417:5: ( formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ';' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:417:9: formalParameters ( '[' ']' )* ( 'throws' qualifiedNameList )? ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_formalParameters_in_interfaceMethodDeclaratorRest1974);
            formalParameters155=formalParameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameters155.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:417:26: ( '[' ']' )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==79) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:417:27: '[' ']'
            	    {
            	    char_literal156=(Token)match(input,79,FOLLOW_79_in_interfaceMethodDeclaratorRest1977); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal156_tree = 
            	    (CommonTree)adaptor.create(char_literal156)
            	    ;
            	    adaptor.addChild(root_0, char_literal156_tree);
            	    }

            	    char_literal157=(Token)match(input,80,FOLLOW_80_in_interfaceMethodDeclaratorRest1979); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal157_tree = 
            	    (CommonTree)adaptor.create(char_literal157)
            	    ;
            	    adaptor.addChild(root_0, char_literal157_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop52;
                }
            } while (true);


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:417:37: ( 'throws' qualifiedNameList )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==118) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:417:38: 'throws' qualifiedNameList
                    {
                    string_literal158=(Token)match(input,118,FOLLOW_118_in_interfaceMethodDeclaratorRest1984); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal158_tree = 
                    (CommonTree)adaptor.create(string_literal158)
                    ;
                    adaptor.addChild(root_0, string_literal158_tree);
                    }

                    pushFollow(FOLLOW_qualifiedNameList_in_interfaceMethodDeclaratorRest1986);
                    qualifiedNameList159=qualifiedNameList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedNameList159.getTree());

                    }
                    break;

            }


            char_literal160=(Token)match(input,SEMI,FOLLOW_SEMI_in_interfaceMethodDeclaratorRest1990); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal160_tree = 
            (CommonTree)adaptor.create(char_literal160)
            ;
            adaptor.addChild(root_0, char_literal160_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 37, interfaceMethodDeclaratorRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "interfaceMethodDeclaratorRest"


    public static class interfaceGenericMethodDecl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "interfaceGenericMethodDecl"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:420:1: interfaceGenericMethodDecl : typeParameters ( type | 'void' ) Identifier interfaceMethodDeclaratorRest ;
    public final JavaParser.interfaceGenericMethodDecl_return interfaceGenericMethodDecl() throws RecognitionException {
        JavaParser.interfaceGenericMethodDecl_return retval = new JavaParser.interfaceGenericMethodDecl_return();
        retval.start = input.LT(1);

        int interfaceGenericMethodDecl_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal163=null;
        Token Identifier164=null;
        JavaParser.typeParameters_return typeParameters161 =null;

        JavaParser.type_return type162 =null;

        JavaParser.interfaceMethodDeclaratorRest_return interfaceMethodDeclaratorRest165 =null;


        CommonTree string_literal163_tree=null;
        CommonTree Identifier164_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:421:5: ( typeParameters ( type | 'void' ) Identifier interfaceMethodDeclaratorRest )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:421:9: typeParameters ( type | 'void' ) Identifier interfaceMethodDeclaratorRest
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_typeParameters_in_interfaceGenericMethodDecl2013);
            typeParameters161=typeParameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeParameters161.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:421:24: ( type | 'void' )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==Identifier||LA54_0==84||LA54_0==86||LA54_0==89||LA54_0==93||LA54_0==98||LA54_0==102||LA54_0==104||LA54_0==112) ) {
                alt54=1;
            }
            else if ( (LA54_0==VOID) ) {
                alt54=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;

            }
            switch (alt54) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:421:25: type
                    {
                    pushFollow(FOLLOW_type_in_interfaceGenericMethodDecl2016);
                    type162=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type162.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:421:32: 'void'
                    {
                    string_literal163=(Token)match(input,VOID,FOLLOW_VOID_in_interfaceGenericMethodDecl2020); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal163_tree = 
                    (CommonTree)adaptor.create(string_literal163)
                    ;
                    adaptor.addChild(root_0, string_literal163_tree);
                    }

                    }
                    break;

            }


            Identifier164=(Token)match(input,Identifier,FOLLOW_Identifier_in_interfaceGenericMethodDecl2023); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier164_tree = 
            (CommonTree)adaptor.create(Identifier164)
            ;
            adaptor.addChild(root_0, Identifier164_tree);
            }

            pushFollow(FOLLOW_interfaceMethodDeclaratorRest_in_interfaceGenericMethodDecl2033);
            interfaceMethodDeclaratorRest165=interfaceMethodDeclaratorRest();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, interfaceMethodDeclaratorRest165.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 38, interfaceGenericMethodDecl_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "interfaceGenericMethodDecl"


    public static class voidInterfaceMethodDeclaratorRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "voidInterfaceMethodDeclaratorRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:425:1: voidInterfaceMethodDeclaratorRest : formalParameters ( 'throws' qualifiedNameList )? ';' ;
    public final JavaParser.voidInterfaceMethodDeclaratorRest_return voidInterfaceMethodDeclaratorRest() throws RecognitionException {
        JavaParser.voidInterfaceMethodDeclaratorRest_return retval = new JavaParser.voidInterfaceMethodDeclaratorRest_return();
        retval.start = input.LT(1);

        int voidInterfaceMethodDeclaratorRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal167=null;
        Token char_literal169=null;
        JavaParser.formalParameters_return formalParameters166 =null;

        JavaParser.qualifiedNameList_return qualifiedNameList168 =null;


        CommonTree string_literal167_tree=null;
        CommonTree char_literal169_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:426:5: ( formalParameters ( 'throws' qualifiedNameList )? ';' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:426:9: formalParameters ( 'throws' qualifiedNameList )? ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_formalParameters_in_voidInterfaceMethodDeclaratorRest2056);
            formalParameters166=formalParameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameters166.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:426:26: ( 'throws' qualifiedNameList )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==118) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:426:27: 'throws' qualifiedNameList
                    {
                    string_literal167=(Token)match(input,118,FOLLOW_118_in_voidInterfaceMethodDeclaratorRest2059); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal167_tree = 
                    (CommonTree)adaptor.create(string_literal167)
                    ;
                    adaptor.addChild(root_0, string_literal167_tree);
                    }

                    pushFollow(FOLLOW_qualifiedNameList_in_voidInterfaceMethodDeclaratorRest2061);
                    qualifiedNameList168=qualifiedNameList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedNameList168.getTree());

                    }
                    break;

            }


            char_literal169=(Token)match(input,SEMI,FOLLOW_SEMI_in_voidInterfaceMethodDeclaratorRest2065); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal169_tree = 
            (CommonTree)adaptor.create(char_literal169)
            ;
            adaptor.addChild(root_0, char_literal169_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 39, voidInterfaceMethodDeclaratorRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "voidInterfaceMethodDeclaratorRest"


    public static class constructorDeclaratorRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constructorDeclaratorRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:429:1: constructorDeclaratorRest : formalParameters ( 'throws' qualifiedNameList )? constructorBody ;
    public final JavaParser.constructorDeclaratorRest_return constructorDeclaratorRest() throws RecognitionException {
        JavaParser.constructorDeclaratorRest_return retval = new JavaParser.constructorDeclaratorRest_return();
        retval.start = input.LT(1);

        int constructorDeclaratorRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal171=null;
        JavaParser.formalParameters_return formalParameters170 =null;

        JavaParser.qualifiedNameList_return qualifiedNameList172 =null;

        JavaParser.constructorBody_return constructorBody173 =null;


        CommonTree string_literal171_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:430:5: ( formalParameters ( 'throws' qualifiedNameList )? constructorBody )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:430:9: formalParameters ( 'throws' qualifiedNameList )? constructorBody
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_formalParameters_in_constructorDeclaratorRest2088);
            formalParameters170=formalParameters();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameters170.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:430:26: ( 'throws' qualifiedNameList )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==118) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:430:27: 'throws' qualifiedNameList
                    {
                    string_literal171=(Token)match(input,118,FOLLOW_118_in_constructorDeclaratorRest2091); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal171_tree = 
                    (CommonTree)adaptor.create(string_literal171)
                    ;
                    adaptor.addChild(root_0, string_literal171_tree);
                    }

                    pushFollow(FOLLOW_qualifiedNameList_in_constructorDeclaratorRest2093);
                    qualifiedNameList172=qualifiedNameList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedNameList172.getTree());

                    }
                    break;

            }


            pushFollow(FOLLOW_constructorBody_in_constructorDeclaratorRest2097);
            constructorBody173=constructorBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, constructorBody173.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 40, constructorDeclaratorRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "constructorDeclaratorRest"


    public static class constantDeclarator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constantDeclarator"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:433:1: constantDeclarator : Identifier constantDeclaratorRest ;
    public final JavaParser.constantDeclarator_return constantDeclarator() throws RecognitionException {
        JavaParser.constantDeclarator_return retval = new JavaParser.constantDeclarator_return();
        retval.start = input.LT(1);

        int constantDeclarator_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier174=null;
        JavaParser.constantDeclaratorRest_return constantDeclaratorRest175 =null;


        CommonTree Identifier174_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:434:5: ( Identifier constantDeclaratorRest )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:434:9: Identifier constantDeclaratorRest
            {
            root_0 = (CommonTree)adaptor.nil();


            Identifier174=(Token)match(input,Identifier,FOLLOW_Identifier_in_constantDeclarator2116); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier174_tree = 
            (CommonTree)adaptor.create(Identifier174)
            ;
            adaptor.addChild(root_0, Identifier174_tree);
            }

            pushFollow(FOLLOW_constantDeclaratorRest_in_constantDeclarator2118);
            constantDeclaratorRest175=constantDeclaratorRest();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, constantDeclaratorRest175.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 41, constantDeclarator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "constantDeclarator"


    public static class variableDeclarators_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "variableDeclarators"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:437:1: variableDeclarators : variableDeclarator ( ',' variableDeclarator )* ;
    public final JavaParser.variableDeclarators_return variableDeclarators() throws RecognitionException {
        JavaParser.variableDeclarators_return retval = new JavaParser.variableDeclarators_return();
        retval.start = input.LT(1);

        int variableDeclarators_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal177=null;
        JavaParser.variableDeclarator_return variableDeclarator176 =null;

        JavaParser.variableDeclarator_return variableDeclarator178 =null;


        CommonTree char_literal177_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:438:5: ( variableDeclarator ( ',' variableDeclarator )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:438:9: variableDeclarator ( ',' variableDeclarator )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_variableDeclarator_in_variableDeclarators2141);
            variableDeclarator176=variableDeclarator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarator176.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:438:28: ( ',' variableDeclarator )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==68) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:438:29: ',' variableDeclarator
            	    {
            	    char_literal177=(Token)match(input,68,FOLLOW_68_in_variableDeclarators2144); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal177_tree = 
            	    (CommonTree)adaptor.create(char_literal177)
            	    ;
            	    adaptor.addChild(root_0, char_literal177_tree);
            	    }

            	    pushFollow(FOLLOW_variableDeclarator_in_variableDeclarators2146);
            	    variableDeclarator178=variableDeclarator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarator178.getTree());

            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 42, variableDeclarators_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "variableDeclarators"


    public static class variableDeclarator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "variableDeclarator"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:441:1: variableDeclarator : variableDeclaratorId ( '=' variableInitializer )? ;
    public final JavaParser.variableDeclarator_return variableDeclarator() throws RecognitionException {
        JavaParser.variableDeclarator_return retval = new JavaParser.variableDeclarator_return();
        retval.start = input.LT(1);

        int variableDeclarator_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal180=null;
        JavaParser.variableDeclaratorId_return variableDeclaratorId179 =null;

        JavaParser.variableInitializer_return variableInitializer181 =null;


        CommonTree char_literal180_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:442:5: ( variableDeclaratorId ( '=' variableInitializer )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:442:9: variableDeclaratorId ( '=' variableInitializer )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_variableDeclaratorId_in_variableDeclarator2167);
            variableDeclaratorId179=variableDeclaratorId();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclaratorId179.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:442:30: ( '=' variableInitializer )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==EQ) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:442:31: '=' variableInitializer
                    {
                    char_literal180=(Token)match(input,EQ,FOLLOW_EQ_in_variableDeclarator2170); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal180_tree = 
                    (CommonTree)adaptor.create(char_literal180)
                    ;
                    adaptor.addChild(root_0, char_literal180_tree);
                    }

                    pushFollow(FOLLOW_variableInitializer_in_variableDeclarator2172);
                    variableInitializer181=variableInitializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableInitializer181.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 43, variableDeclarator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "variableDeclarator"


    public static class constantDeclaratorsRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constantDeclaratorsRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:445:1: constantDeclaratorsRest : constantDeclaratorRest ( ',' constantDeclarator )* ;
    public final JavaParser.constantDeclaratorsRest_return constantDeclaratorsRest() throws RecognitionException {
        JavaParser.constantDeclaratorsRest_return retval = new JavaParser.constantDeclaratorsRest_return();
        retval.start = input.LT(1);

        int constantDeclaratorsRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal183=null;
        JavaParser.constantDeclaratorRest_return constantDeclaratorRest182 =null;

        JavaParser.constantDeclarator_return constantDeclarator184 =null;


        CommonTree char_literal183_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:446:5: ( constantDeclaratorRest ( ',' constantDeclarator )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:446:9: constantDeclaratorRest ( ',' constantDeclarator )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_constantDeclaratorRest_in_constantDeclaratorsRest2197);
            constantDeclaratorRest182=constantDeclaratorRest();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, constantDeclaratorRest182.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:446:32: ( ',' constantDeclarator )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==68) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:446:33: ',' constantDeclarator
            	    {
            	    char_literal183=(Token)match(input,68,FOLLOW_68_in_constantDeclaratorsRest2200); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal183_tree = 
            	    (CommonTree)adaptor.create(char_literal183)
            	    ;
            	    adaptor.addChild(root_0, char_literal183_tree);
            	    }

            	    pushFollow(FOLLOW_constantDeclarator_in_constantDeclaratorsRest2202);
            	    constantDeclarator184=constantDeclarator();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, constantDeclarator184.getTree());

            	    }
            	    break;

            	default :
            	    break loop59;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 44, constantDeclaratorsRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "constantDeclaratorsRest"


    public static class constantDeclaratorRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constantDeclaratorRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:449:1: constantDeclaratorRest : ( '[' ']' )* '=' variableInitializer ;
    public final JavaParser.constantDeclaratorRest_return constantDeclaratorRest() throws RecognitionException {
        JavaParser.constantDeclaratorRest_return retval = new JavaParser.constantDeclaratorRest_return();
        retval.start = input.LT(1);

        int constantDeclaratorRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal185=null;
        Token char_literal186=null;
        Token char_literal187=null;
        JavaParser.variableInitializer_return variableInitializer188 =null;


        CommonTree char_literal185_tree=null;
        CommonTree char_literal186_tree=null;
        CommonTree char_literal187_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:450:5: ( ( '[' ']' )* '=' variableInitializer )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:450:9: ( '[' ']' )* '=' variableInitializer
            {
            root_0 = (CommonTree)adaptor.nil();


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:450:9: ( '[' ']' )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==79) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:450:10: '[' ']'
            	    {
            	    char_literal185=(Token)match(input,79,FOLLOW_79_in_constantDeclaratorRest2224); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal185_tree = 
            	    (CommonTree)adaptor.create(char_literal185)
            	    ;
            	    adaptor.addChild(root_0, char_literal185_tree);
            	    }

            	    char_literal186=(Token)match(input,80,FOLLOW_80_in_constantDeclaratorRest2226); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal186_tree = 
            	    (CommonTree)adaptor.create(char_literal186)
            	    ;
            	    adaptor.addChild(root_0, char_literal186_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop60;
                }
            } while (true);


            char_literal187=(Token)match(input,EQ,FOLLOW_EQ_in_constantDeclaratorRest2230); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal187_tree = 
            (CommonTree)adaptor.create(char_literal187)
            ;
            adaptor.addChild(root_0, char_literal187_tree);
            }

            pushFollow(FOLLOW_variableInitializer_in_constantDeclaratorRest2232);
            variableInitializer188=variableInitializer();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableInitializer188.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 45, constantDeclaratorRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "constantDeclaratorRest"


    public static class variableDeclaratorId_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "variableDeclaratorId"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:453:1: variableDeclaratorId : Identifier ( '[' ']' )* ;
    public final JavaParser.variableDeclaratorId_return variableDeclaratorId() throws RecognitionException {
        JavaParser.variableDeclaratorId_return retval = new JavaParser.variableDeclaratorId_return();
        retval.start = input.LT(1);

        int variableDeclaratorId_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier189=null;
        Token char_literal190=null;
        Token char_literal191=null;

        CommonTree Identifier189_tree=null;
        CommonTree char_literal190_tree=null;
        CommonTree char_literal191_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:454:5: ( Identifier ( '[' ']' )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:454:9: Identifier ( '[' ']' )*
            {
            root_0 = (CommonTree)adaptor.nil();


            Identifier189=(Token)match(input,Identifier,FOLLOW_Identifier_in_variableDeclaratorId2255); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier189_tree = 
            (CommonTree)adaptor.create(Identifier189)
            ;
            adaptor.addChild(root_0, Identifier189_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:454:20: ( '[' ']' )*
            loop61:
            do {
                int alt61=2;
                int LA61_0 = input.LA(1);

                if ( (LA61_0==79) ) {
                    alt61=1;
                }


                switch (alt61) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:454:21: '[' ']'
            	    {
            	    char_literal190=(Token)match(input,79,FOLLOW_79_in_variableDeclaratorId2258); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal190_tree = 
            	    (CommonTree)adaptor.create(char_literal190)
            	    ;
            	    adaptor.addChild(root_0, char_literal190_tree);
            	    }

            	    char_literal191=(Token)match(input,80,FOLLOW_80_in_variableDeclaratorId2260); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal191_tree = 
            	    (CommonTree)adaptor.create(char_literal191)
            	    ;
            	    adaptor.addChild(root_0, char_literal191_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop61;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 46, variableDeclaratorId_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "variableDeclaratorId"


    public static class variableInitializer_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "variableInitializer"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:457:1: variableInitializer : ( arrayInitializer | expression );
    public final JavaParser.variableInitializer_return variableInitializer() throws RecognitionException {
        JavaParser.variableInitializer_return retval = new JavaParser.variableInitializer_return();
        retval.start = input.LT(1);

        int variableInitializer_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.arrayInitializer_return arrayInitializer192 =null;

        JavaParser.expression_return expression193 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:458:5: ( arrayInitializer | expression )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==124) ) {
                alt62=1;
            }
            else if ( (LA62_0==CharacterLiteral||LA62_0==DecimalLiteral||LA62_0==FloatingPointLiteral||LA62_0==HexLiteral||LA62_0==Identifier||LA62_0==OctalLiteral||(LA62_0 >= SUPER && LA62_0 <= StringLiteral)||LA62_0==VOID||LA62_0==55||LA62_0==62||(LA62_0 >= 65 && LA62_0 <= 66)||(LA62_0 >= 69 && LA62_0 <= 70)||LA62_0==84||LA62_0==86||LA62_0==89||LA62_0==93||LA62_0==95||LA62_0==98||LA62_0==102||LA62_0==104||(LA62_0 >= 106 && LA62_0 <= 107)||LA62_0==112||LA62_0==116||LA62_0==120||LA62_0==129) ) {
                alt62=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;

            }
            switch (alt62) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:458:9: arrayInitializer
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_arrayInitializer_in_variableInitializer2281);
                    arrayInitializer192=arrayInitializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayInitializer192.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:459:9: expression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_expression_in_variableInitializer2291);
                    expression193=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression193.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 47, variableInitializer_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "variableInitializer"


    public static class arrayInitializer_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arrayInitializer"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:462:1: arrayInitializer : '{' ( variableInitializer ( ',' variableInitializer )* ( ',' )? )? '}' ;
    public final JavaParser.arrayInitializer_return arrayInitializer() throws RecognitionException {
        JavaParser.arrayInitializer_return retval = new JavaParser.arrayInitializer_return();
        retval.start = input.LT(1);

        int arrayInitializer_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal194=null;
        Token char_literal196=null;
        Token char_literal198=null;
        Token char_literal199=null;
        JavaParser.variableInitializer_return variableInitializer195 =null;

        JavaParser.variableInitializer_return variableInitializer197 =null;


        CommonTree char_literal194_tree=null;
        CommonTree char_literal196_tree=null;
        CommonTree char_literal198_tree=null;
        CommonTree char_literal199_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:463:5: ( '{' ( variableInitializer ( ',' variableInitializer )* ( ',' )? )? '}' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:463:9: '{' ( variableInitializer ( ',' variableInitializer )* ( ',' )? )? '}'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal194=(Token)match(input,124,FOLLOW_124_in_arrayInitializer2318); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal194_tree = 
            (CommonTree)adaptor.create(char_literal194)
            ;
            adaptor.addChild(root_0, char_literal194_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:463:13: ( variableInitializer ( ',' variableInitializer )* ( ',' )? )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==CharacterLiteral||LA65_0==DecimalLiteral||LA65_0==FloatingPointLiteral||LA65_0==HexLiteral||LA65_0==Identifier||LA65_0==OctalLiteral||(LA65_0 >= SUPER && LA65_0 <= StringLiteral)||LA65_0==VOID||LA65_0==55||LA65_0==62||(LA65_0 >= 65 && LA65_0 <= 66)||(LA65_0 >= 69 && LA65_0 <= 70)||LA65_0==84||LA65_0==86||LA65_0==89||LA65_0==93||LA65_0==95||LA65_0==98||LA65_0==102||LA65_0==104||(LA65_0 >= 106 && LA65_0 <= 107)||LA65_0==112||LA65_0==116||LA65_0==120||LA65_0==124||LA65_0==129) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:463:14: variableInitializer ( ',' variableInitializer )* ( ',' )?
                    {
                    pushFollow(FOLLOW_variableInitializer_in_arrayInitializer2321);
                    variableInitializer195=variableInitializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableInitializer195.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:463:34: ( ',' variableInitializer )*
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==68) ) {
                            int LA63_1 = input.LA(2);

                            if ( (LA63_1==CharacterLiteral||LA63_1==DecimalLiteral||LA63_1==FloatingPointLiteral||LA63_1==HexLiteral||LA63_1==Identifier||LA63_1==OctalLiteral||(LA63_1 >= SUPER && LA63_1 <= StringLiteral)||LA63_1==VOID||LA63_1==55||LA63_1==62||(LA63_1 >= 65 && LA63_1 <= 66)||(LA63_1 >= 69 && LA63_1 <= 70)||LA63_1==84||LA63_1==86||LA63_1==89||LA63_1==93||LA63_1==95||LA63_1==98||LA63_1==102||LA63_1==104||(LA63_1 >= 106 && LA63_1 <= 107)||LA63_1==112||LA63_1==116||LA63_1==120||LA63_1==124||LA63_1==129) ) {
                                alt63=1;
                            }


                        }


                        switch (alt63) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:463:35: ',' variableInitializer
                    	    {
                    	    char_literal196=(Token)match(input,68,FOLLOW_68_in_arrayInitializer2324); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal196_tree = 
                    	    (CommonTree)adaptor.create(char_literal196)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal196_tree);
                    	    }

                    	    pushFollow(FOLLOW_variableInitializer_in_arrayInitializer2326);
                    	    variableInitializer197=variableInitializer();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableInitializer197.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop63;
                        }
                    } while (true);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:463:61: ( ',' )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==68) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:463:62: ','
                            {
                            char_literal198=(Token)match(input,68,FOLLOW_68_in_arrayInitializer2331); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal198_tree = 
                            (CommonTree)adaptor.create(char_literal198)
                            ;
                            adaptor.addChild(root_0, char_literal198_tree);
                            }

                            }
                            break;

                    }


                    }
                    break;

            }


            char_literal199=(Token)match(input,128,FOLLOW_128_in_arrayInitializer2338); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal199_tree = 
            (CommonTree)adaptor.create(char_literal199)
            ;
            adaptor.addChild(root_0, char_literal199_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 48, arrayInitializer_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "arrayInitializer"


    public static class modifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "modifier"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:466:1: modifier : ( annotation | 'public' | 'protected' | 'private' | STATIC | 'abstract' | 'final' | 'native' | 'synchronized' | 'transient' | 'volatile' | 'strictfp' );
    public final JavaParser.modifier_return modifier() throws RecognitionException {
        JavaParser.modifier_return retval = new JavaParser.modifier_return();
        retval.start = input.LT(1);

        int modifier_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal201=null;
        Token string_literal202=null;
        Token string_literal203=null;
        Token STATIC204=null;
        Token string_literal205=null;
        Token string_literal206=null;
        Token string_literal207=null;
        Token string_literal208=null;
        Token string_literal209=null;
        Token string_literal210=null;
        Token string_literal211=null;
        JavaParser.annotation_return annotation200 =null;


        CommonTree string_literal201_tree=null;
        CommonTree string_literal202_tree=null;
        CommonTree string_literal203_tree=null;
        CommonTree STATIC204_tree=null;
        CommonTree string_literal205_tree=null;
        CommonTree string_literal206_tree=null;
        CommonTree string_literal207_tree=null;
        CommonTree string_literal208_tree=null;
        CommonTree string_literal209_tree=null;
        CommonTree string_literal210_tree=null;
        CommonTree string_literal211_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:467:5: ( annotation | 'public' | 'protected' | 'private' | STATIC | 'abstract' | 'final' | 'native' | 'synchronized' | 'transient' | 'volatile' | 'strictfp' )
            int alt66=12;
            switch ( input.LA(1) ) {
            case 78:
                {
                alt66=1;
                }
                break;
            case 110:
                {
                alt66=2;
                }
                break;
            case 109:
                {
                alt66=3;
                }
                break;
            case 108:
                {
                alt66=4;
                }
                break;
            case STATIC:
                {
                alt66=5;
                }
                break;
            case 83:
                {
                alt66=6;
                }
                break;
            case 96:
                {
                alt66=7;
                }
                break;
            case 105:
                {
                alt66=8;
                }
                break;
            case 115:
                {
                alt66=9;
                }
                break;
            case 119:
                {
                alt66=10;
                }
                break;
            case 122:
                {
                alt66=11;
                }
                break;
            case 113:
                {
                alt66=12;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;

            }

            switch (alt66) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:467:9: annotation
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_annotation_in_modifier2357);
                    annotation200=annotation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation200.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:468:9: 'public'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal201=(Token)match(input,110,FOLLOW_110_in_modifier2367); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal201_tree = 
                    (CommonTree)adaptor.create(string_literal201)
                    ;
                    adaptor.addChild(root_0, string_literal201_tree);
                    }

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:469:9: 'protected'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal202=(Token)match(input,109,FOLLOW_109_in_modifier2377); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal202_tree = 
                    (CommonTree)adaptor.create(string_literal202)
                    ;
                    adaptor.addChild(root_0, string_literal202_tree);
                    }

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:470:9: 'private'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal203=(Token)match(input,108,FOLLOW_108_in_modifier2387); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal203_tree = 
                    (CommonTree)adaptor.create(string_literal203)
                    ;
                    adaptor.addChild(root_0, string_literal203_tree);
                    }

                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:471:10: STATIC
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    STATIC204=(Token)match(input,STATIC,FOLLOW_STATIC_in_modifier2398); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STATIC204_tree = 
                    (CommonTree)adaptor.create(STATIC204)
                    ;
                    adaptor.addChild(root_0, STATIC204_tree);
                    }

                    }
                    break;
                case 6 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:472:9: 'abstract'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal205=(Token)match(input,83,FOLLOW_83_in_modifier2408); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal205_tree = 
                    (CommonTree)adaptor.create(string_literal205)
                    ;
                    adaptor.addChild(root_0, string_literal205_tree);
                    }

                    }
                    break;
                case 7 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:473:9: 'final'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal206=(Token)match(input,96,FOLLOW_96_in_modifier2418); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal206_tree = 
                    (CommonTree)adaptor.create(string_literal206)
                    ;
                    adaptor.addChild(root_0, string_literal206_tree);
                    }

                    }
                    break;
                case 8 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:474:9: 'native'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal207=(Token)match(input,105,FOLLOW_105_in_modifier2428); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal207_tree = 
                    (CommonTree)adaptor.create(string_literal207)
                    ;
                    adaptor.addChild(root_0, string_literal207_tree);
                    }

                    }
                    break;
                case 9 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:475:9: 'synchronized'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal208=(Token)match(input,115,FOLLOW_115_in_modifier2438); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal208_tree = 
                    (CommonTree)adaptor.create(string_literal208)
                    ;
                    adaptor.addChild(root_0, string_literal208_tree);
                    }

                    }
                    break;
                case 10 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:476:9: 'transient'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal209=(Token)match(input,119,FOLLOW_119_in_modifier2448); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal209_tree = 
                    (CommonTree)adaptor.create(string_literal209)
                    ;
                    adaptor.addChild(root_0, string_literal209_tree);
                    }

                    }
                    break;
                case 11 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:477:9: 'volatile'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal210=(Token)match(input,122,FOLLOW_122_in_modifier2458); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal210_tree = 
                    (CommonTree)adaptor.create(string_literal210)
                    ;
                    adaptor.addChild(root_0, string_literal210_tree);
                    }

                    }
                    break;
                case 12 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:478:9: 'strictfp'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal211=(Token)match(input,113,FOLLOW_113_in_modifier2468); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal211_tree = 
                    (CommonTree)adaptor.create(string_literal211)
                    ;
                    adaptor.addChild(root_0, string_literal211_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 49, modifier_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "modifier"


    public static class packageOrTypeName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "packageOrTypeName"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:481:1: packageOrTypeName : qualifiedName ;
    public final JavaParser.packageOrTypeName_return packageOrTypeName() throws RecognitionException {
        JavaParser.packageOrTypeName_return retval = new JavaParser.packageOrTypeName_return();
        retval.start = input.LT(1);

        int packageOrTypeName_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.qualifiedName_return qualifiedName212 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:482:5: ( qualifiedName )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:482:9: qualifiedName
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_qualifiedName_in_packageOrTypeName2487);
            qualifiedName212=qualifiedName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedName212.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 50, packageOrTypeName_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "packageOrTypeName"


    public static class enumConstantName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "enumConstantName"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:485:1: enumConstantName : Identifier ;
    public final JavaParser.enumConstantName_return enumConstantName() throws RecognitionException {
        JavaParser.enumConstantName_return retval = new JavaParser.enumConstantName_return();
        retval.start = input.LT(1);

        int enumConstantName_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier213=null;

        CommonTree Identifier213_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:486:5: ( Identifier )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:486:9: Identifier
            {
            root_0 = (CommonTree)adaptor.nil();


            Identifier213=(Token)match(input,Identifier,FOLLOW_Identifier_in_enumConstantName2506); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier213_tree = 
            (CommonTree)adaptor.create(Identifier213)
            ;
            adaptor.addChild(root_0, Identifier213_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 51, enumConstantName_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "enumConstantName"


    public static class typeName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeName"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:489:1: typeName : qualifiedName ;
    public final JavaParser.typeName_return typeName() throws RecognitionException {
        JavaParser.typeName_return retval = new JavaParser.typeName_return();
        retval.start = input.LT(1);

        int typeName_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.qualifiedName_return qualifiedName214 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:490:5: ( qualifiedName )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:490:9: qualifiedName
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_qualifiedName_in_typeName2525);
            qualifiedName214=qualifiedName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedName214.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 52, typeName_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "typeName"


    public static class type_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "type"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:493:1: type : ( classOrInterfaceType ( '[' ']' )* -> ^( TYPE classOrInterfaceType ( '[' ']' )* ) | primitiveType ( '[' ']' )* -> ^( TYPE primitiveType ( '[' ']' )* ) );
    public final JavaParser.type_return type() throws RecognitionException {
        JavaParser.type_return retval = new JavaParser.type_return();
        retval.start = input.LT(1);

        int type_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal216=null;
        Token char_literal217=null;
        Token char_literal219=null;
        Token char_literal220=null;
        JavaParser.classOrInterfaceType_return classOrInterfaceType215 =null;

        JavaParser.primitiveType_return primitiveType218 =null;


        CommonTree char_literal216_tree=null;
        CommonTree char_literal217_tree=null;
        CommonTree char_literal219_tree=null;
        CommonTree char_literal220_tree=null;
        RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
        RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
        RewriteRuleSubtreeStream stream_primitiveType=new RewriteRuleSubtreeStream(adaptor,"rule primitiveType");
        RewriteRuleSubtreeStream stream_classOrInterfaceType=new RewriteRuleSubtreeStream(adaptor,"rule classOrInterfaceType");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:494:2: ( classOrInterfaceType ( '[' ']' )* -> ^( TYPE classOrInterfaceType ( '[' ']' )* ) | primitiveType ( '[' ']' )* -> ^( TYPE primitiveType ( '[' ']' )* ) )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==Identifier) ) {
                alt69=1;
            }
            else if ( (LA69_0==84||LA69_0==86||LA69_0==89||LA69_0==93||LA69_0==98||LA69_0==102||LA69_0==104||LA69_0==112) ) {
                alt69=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;

            }
            switch (alt69) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:494:4: classOrInterfaceType ( '[' ']' )*
                    {
                    pushFollow(FOLLOW_classOrInterfaceType_in_type2539);
                    classOrInterfaceType215=classOrInterfaceType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_classOrInterfaceType.add(classOrInterfaceType215.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:494:25: ( '[' ']' )*
                    loop67:
                    do {
                        int alt67=2;
                        int LA67_0 = input.LA(1);

                        if ( (LA67_0==79) ) {
                            alt67=1;
                        }


                        switch (alt67) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:494:26: '[' ']'
                    	    {
                    	    char_literal216=(Token)match(input,79,FOLLOW_79_in_type2542); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_79.add(char_literal216);


                    	    char_literal217=(Token)match(input,80,FOLLOW_80_in_type2544); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_80.add(char_literal217);


                    	    }
                    	    break;

                    	default :
                    	    break loop67;
                        }
                    } while (true);


                    // AST REWRITE
                    // elements: classOrInterfaceType, 79, 80
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 494:36: -> ^( TYPE classOrInterfaceType ( '[' ']' )* )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:494:39: ^( TYPE classOrInterfaceType ( '[' ']' )* )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TYPE, "TYPE")
                        , root_1);

                        adaptor.addChild(root_1, stream_classOrInterfaceType.nextTree());

                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:494:67: ( '[' ']' )*
                        while ( stream_79.hasNext()||stream_80.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_79.nextNode()
                            );

                            adaptor.addChild(root_1, 
                            stream_80.nextNode()
                            );

                        }
                        stream_79.reset();
                        stream_80.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:495:4: primitiveType ( '[' ']' )*
                    {
                    pushFollow(FOLLOW_primitiveType_in_type2566);
                    primitiveType218=primitiveType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_primitiveType.add(primitiveType218.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:495:18: ( '[' ']' )*
                    loop68:
                    do {
                        int alt68=2;
                        int LA68_0 = input.LA(1);

                        if ( (LA68_0==79) ) {
                            alt68=1;
                        }


                        switch (alt68) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:495:19: '[' ']'
                    	    {
                    	    char_literal219=(Token)match(input,79,FOLLOW_79_in_type2569); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_79.add(char_literal219);


                    	    char_literal220=(Token)match(input,80,FOLLOW_80_in_type2571); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_80.add(char_literal220);


                    	    }
                    	    break;

                    	default :
                    	    break loop68;
                        }
                    } while (true);


                    // AST REWRITE
                    // elements: 79, primitiveType, 80
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 495:29: -> ^( TYPE primitiveType ( '[' ']' )* )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:495:32: ^( TYPE primitiveType ( '[' ']' )* )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(TYPE, "TYPE")
                        , root_1);

                        adaptor.addChild(root_1, stream_primitiveType.nextTree());

                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:495:53: ( '[' ']' )*
                        while ( stream_79.hasNext()||stream_80.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_79.nextNode()
                            );

                            adaptor.addChild(root_1, 
                            stream_80.nextNode()
                            );

                        }
                        stream_79.reset();
                        stream_80.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 53, type_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "type"


    public static class classOrInterfaceType_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "classOrInterfaceType"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:498:1: classOrInterfaceType : ( Identifier -> Identifier ( typeArguments )? ) ( DOT ident= Identifier ( typeArguments )? -> ^( DOT $classOrInterfaceType $ident) )* ;
    public final JavaParser.classOrInterfaceType_return classOrInterfaceType() throws RecognitionException {
        JavaParser.classOrInterfaceType_return retval = new JavaParser.classOrInterfaceType_return();
        retval.start = input.LT(1);

        int classOrInterfaceType_StartIndex = input.index();

        CommonTree root_0 = null;

        Token ident=null;
        Token Identifier221=null;
        Token DOT222=null;
        JavaParser.typeArguments_return typeArguments223 =null;


        CommonTree ident_tree=null;
        CommonTree Identifier221_tree=null;
        CommonTree DOT222_tree=null;
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_typeArguments=new RewriteRuleSubtreeStream(adaptor,"rule typeArguments");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:499:2: ( ( Identifier -> Identifier ( typeArguments )? ) ( DOT ident= Identifier ( typeArguments )? -> ^( DOT $classOrInterfaceType $ident) )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:499:4: ( Identifier -> Identifier ( typeArguments )? ) ( DOT ident= Identifier ( typeArguments )? -> ^( DOT $classOrInterfaceType $ident) )*
            {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:499:4: ( Identifier -> Identifier ( typeArguments )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:499:5: Identifier
            {
            Identifier221=(Token)match(input,Identifier,FOLLOW_Identifier_in_classOrInterfaceType2601); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier221);


            // AST REWRITE
            // elements: typeArguments, Identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 499:16: -> Identifier ( typeArguments )?
            {
                adaptor.addChild(root_0, 
                stream_Identifier.nextNode()
                );

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:499:30: ( typeArguments )?
                if ( stream_typeArguments.hasNext() ) {
                    adaptor.addChild(root_0, stream_typeArguments.nextTree());

                }
                stream_typeArguments.reset();

            }


            retval.tree = root_0;
            }

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:499:47: ( DOT ident= Identifier ( typeArguments )? -> ^( DOT $classOrInterfaceType $ident) )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==DOT) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:499:48: DOT ident= Identifier ( typeArguments )?
            	    {
            	    DOT222=(Token)match(input,DOT,FOLLOW_DOT_in_classOrInterfaceType2613); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_DOT.add(DOT222);


            	    ident=(Token)match(input,Identifier,FOLLOW_Identifier_in_classOrInterfaceType2617); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Identifier.add(ident);


            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:499:69: ( typeArguments )?
            	    int alt70=2;
            	    int LA70_0 = input.LA(1);

            	    if ( (LA70_0==LS) ) {
            	        int LA70_1 = input.LA(2);

            	        if ( (LA70_1==Identifier||LA70_1==77||LA70_1==84||LA70_1==86||LA70_1==89||LA70_1==93||LA70_1==98||LA70_1==102||LA70_1==104||LA70_1==112) ) {
            	            alt70=1;
            	        }
            	    }
            	    switch (alt70) {
            	        case 1 :
            	            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:499:69: typeArguments
            	            {
            	            pushFollow(FOLLOW_typeArguments_in_classOrInterfaceType2619);
            	            typeArguments223=typeArguments();

            	            state._fsp--;
            	            if (state.failed) return retval;
            	            if ( state.backtracking==0 ) stream_typeArguments.add(typeArguments223.getTree());

            	            }
            	            break;

            	    }


            	    // AST REWRITE
            	    // elements: DOT, ident, classOrInterfaceType
            	    // token labels: ident
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleTokenStream stream_ident=new RewriteRuleTokenStream(adaptor,"token ident",ident);
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (CommonTree)adaptor.nil();
            	    // 499:84: -> ^( DOT $classOrInterfaceType $ident)
            	    {
            	        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:499:87: ^( DOT $classOrInterfaceType $ident)
            	        {
            	        CommonTree root_1 = (CommonTree)adaptor.nil();
            	        root_1 = (CommonTree)adaptor.becomeRoot(
            	        stream_DOT.nextNode()
            	        , root_1);

            	        adaptor.addChild(root_1, stream_retval.nextTree());

            	        adaptor.addChild(root_1, stream_ident.nextNode());

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop71;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 54, classOrInterfaceType_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "classOrInterfaceType"


    public static class primitiveType_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "primitiveType"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:502:1: primitiveType : ( 'boolean' | 'char' | 'byte' | 'short' | 'int' | 'long' | 'float' | 'double' );
    public final JavaParser.primitiveType_return primitiveType() throws RecognitionException {
        JavaParser.primitiveType_return retval = new JavaParser.primitiveType_return();
        retval.start = input.LT(1);

        int primitiveType_StartIndex = input.index();

        CommonTree root_0 = null;

        Token set224=null;

        CommonTree set224_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 55) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:503:5: ( 'boolean' | 'char' | 'byte' | 'short' | 'int' | 'long' | 'float' | 'double' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            set224=(Token)input.LT(1);

            if ( input.LA(1)==84||input.LA(1)==86||input.LA(1)==89||input.LA(1)==93||input.LA(1)==98||input.LA(1)==102||input.LA(1)==104||input.LA(1)==112 ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set224)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 55, primitiveType_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "primitiveType"


    public static class variableModifier_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "variableModifier"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:513:1: variableModifier : ( 'final' | annotation );
    public final JavaParser.variableModifier_return variableModifier() throws RecognitionException {
        JavaParser.variableModifier_return retval = new JavaParser.variableModifier_return();
        retval.start = input.LT(1);

        int variableModifier_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal225=null;
        JavaParser.annotation_return annotation226 =null;


        CommonTree string_literal225_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 56) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:514:5: ( 'final' | annotation )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==96) ) {
                alt72=1;
            }
            else if ( (LA72_0==78) ) {
                alt72=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;

            }
            switch (alt72) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:514:9: 'final'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal225=(Token)match(input,96,FOLLOW_96_in_variableModifier2740); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal225_tree = 
                    (CommonTree)adaptor.create(string_literal225)
                    ;
                    adaptor.addChild(root_0, string_literal225_tree);
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:515:9: annotation
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_annotation_in_variableModifier2750);
                    annotation226=annotation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation226.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 56, variableModifier_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "variableModifier"


    public static class typeArguments_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeArguments"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:518:1: typeArguments : '<' typeArgument ( ',' typeArgument )* '>' ;
    public final JavaParser.typeArguments_return typeArguments() throws RecognitionException {
        JavaParser.typeArguments_return retval = new JavaParser.typeArguments_return();
        retval.start = input.LT(1);

        int typeArguments_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal227=null;
        Token char_literal229=null;
        Token char_literal231=null;
        JavaParser.typeArgument_return typeArgument228 =null;

        JavaParser.typeArgument_return typeArgument230 =null;


        CommonTree char_literal227_tree=null;
        CommonTree char_literal229_tree=null;
        CommonTree char_literal231_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 57) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:519:5: ( '<' typeArgument ( ',' typeArgument )* '>' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:519:9: '<' typeArgument ( ',' typeArgument )* '>'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal227=(Token)match(input,LS,FOLLOW_LS_in_typeArguments2769); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal227_tree = 
            (CommonTree)adaptor.create(char_literal227)
            ;
            adaptor.addChild(root_0, char_literal227_tree);
            }

            pushFollow(FOLLOW_typeArgument_in_typeArguments2771);
            typeArgument228=typeArgument();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeArgument228.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:519:26: ( ',' typeArgument )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==68) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:519:27: ',' typeArgument
            	    {
            	    char_literal229=(Token)match(input,68,FOLLOW_68_in_typeArguments2774); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal229_tree = 
            	    (CommonTree)adaptor.create(char_literal229)
            	    ;
            	    adaptor.addChild(root_0, char_literal229_tree);
            	    }

            	    pushFollow(FOLLOW_typeArgument_in_typeArguments2776);
            	    typeArgument230=typeArgument();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, typeArgument230.getTree());

            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);


            char_literal231=(Token)match(input,GT,FOLLOW_GT_in_typeArguments2780); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal231_tree = 
            (CommonTree)adaptor.create(char_literal231)
            ;
            adaptor.addChild(root_0, char_literal231_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 57, typeArguments_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "typeArguments"


    public static class typeArgument_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "typeArgument"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:522:1: typeArgument : ( type | '?' ( ( EXTENDS | SUPER ) type )? );
    public final JavaParser.typeArgument_return typeArgument() throws RecognitionException {
        JavaParser.typeArgument_return retval = new JavaParser.typeArgument_return();
        retval.start = input.LT(1);

        int typeArgument_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal233=null;
        Token set234=null;
        JavaParser.type_return type232 =null;

        JavaParser.type_return type235 =null;


        CommonTree char_literal233_tree=null;
        CommonTree set234_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 58) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:523:5: ( type | '?' ( ( EXTENDS | SUPER ) type )? )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==Identifier||LA75_0==84||LA75_0==86||LA75_0==89||LA75_0==93||LA75_0==98||LA75_0==102||LA75_0==104||LA75_0==112) ) {
                alt75=1;
            }
            else if ( (LA75_0==77) ) {
                alt75=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;

            }
            switch (alt75) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:523:9: type
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_type_in_typeArgument2803);
                    type232=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type232.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:524:9: '?' ( ( EXTENDS | SUPER ) type )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal233=(Token)match(input,77,FOLLOW_77_in_typeArgument2813); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal233_tree = 
                    (CommonTree)adaptor.create(char_literal233)
                    ;
                    adaptor.addChild(root_0, char_literal233_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:524:13: ( ( EXTENDS | SUPER ) type )?
                    int alt74=2;
                    int LA74_0 = input.LA(1);

                    if ( (LA74_0==EXTENDS||LA74_0==SUPER) ) {
                        alt74=1;
                    }
                    switch (alt74) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:524:14: ( EXTENDS | SUPER ) type
                            {
                            set234=(Token)input.LT(1);

                            if ( input.LA(1)==EXTENDS||input.LA(1)==SUPER ) {
                                input.consume();
                                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                                (CommonTree)adaptor.create(set234)
                                );
                                state.errorRecovery=false;
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            pushFollow(FOLLOW_type_in_typeArgument2824);
                            type235=type();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, type235.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 58, typeArgument_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "typeArgument"


    public static class qualifiedNameList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "qualifiedNameList"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:527:1: qualifiedNameList : qualifiedName ( ',' qualifiedName )* ;
    public final JavaParser.qualifiedNameList_return qualifiedNameList() throws RecognitionException {
        JavaParser.qualifiedNameList_return retval = new JavaParser.qualifiedNameList_return();
        retval.start = input.LT(1);

        int qualifiedNameList_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal237=null;
        JavaParser.qualifiedName_return qualifiedName236 =null;

        JavaParser.qualifiedName_return qualifiedName238 =null;


        CommonTree char_literal237_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 59) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:528:5: ( qualifiedName ( ',' qualifiedName )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:528:9: qualifiedName ( ',' qualifiedName )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_qualifiedName_in_qualifiedNameList2849);
            qualifiedName236=qualifiedName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedName236.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:528:23: ( ',' qualifiedName )*
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( (LA76_0==68) ) {
                    alt76=1;
                }


                switch (alt76) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:528:24: ',' qualifiedName
            	    {
            	    char_literal237=(Token)match(input,68,FOLLOW_68_in_qualifiedNameList2852); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal237_tree = 
            	    (CommonTree)adaptor.create(char_literal237)
            	    ;
            	    adaptor.addChild(root_0, char_literal237_tree);
            	    }

            	    pushFollow(FOLLOW_qualifiedName_in_qualifiedNameList2854);
            	    qualifiedName238=qualifiedName();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, qualifiedName238.getTree());

            	    }
            	    break;

            	default :
            	    break loop76;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 59, qualifiedNameList_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "qualifiedNameList"


    public static class formalParameters_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "formalParameters"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:531:1: formalParameters : '(' ( formalParameterDecls )? ')' -> ^( PARAMETERS ( formalParameterDecls )? ) ;
    public final JavaParser.formalParameters_return formalParameters() throws RecognitionException {
        JavaParser.formalParameters_return retval = new JavaParser.formalParameters_return();
        retval.start = input.LT(1);

        int formalParameters_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal239=null;
        Token char_literal241=null;
        JavaParser.formalParameterDecls_return formalParameterDecls240 =null;


        CommonTree char_literal239_tree=null;
        CommonTree char_literal241_tree=null;
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleSubtreeStream stream_formalParameterDecls=new RewriteRuleSubtreeStream(adaptor,"rule formalParameterDecls");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 60) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:532:5: ( '(' ( formalParameterDecls )? ')' -> ^( PARAMETERS ( formalParameterDecls )? ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:532:9: '(' ( formalParameterDecls )? ')'
            {
            char_literal239=(Token)match(input,62,FOLLOW_62_in_formalParameters2875); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_62.add(char_literal239);


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:532:13: ( formalParameterDecls )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==Identifier||LA77_0==78||LA77_0==84||LA77_0==86||LA77_0==89||LA77_0==93||LA77_0==96||LA77_0==98||LA77_0==102||LA77_0==104||LA77_0==112) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:532:13: formalParameterDecls
                    {
                    pushFollow(FOLLOW_formalParameterDecls_in_formalParameters2877);
                    formalParameterDecls240=formalParameterDecls();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_formalParameterDecls.add(formalParameterDecls240.getTree());

                    }
                    break;

            }


            char_literal241=(Token)match(input,63,FOLLOW_63_in_formalParameters2880); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_63.add(char_literal241);


            // AST REWRITE
            // elements: formalParameterDecls
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 532:39: -> ^( PARAMETERS ( formalParameterDecls )? )
            {
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:532:42: ^( PARAMETERS ( formalParameterDecls )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(PARAMETERS, "PARAMETERS")
                , root_1);

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:532:55: ( formalParameterDecls )?
                if ( stream_formalParameterDecls.hasNext() ) {
                    adaptor.addChild(root_1, stream_formalParameterDecls.nextTree());

                }
                stream_formalParameterDecls.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 60, formalParameters_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "formalParameters"


    public static class formalParameterDecls_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "formalParameterDecls"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:535:1: formalParameterDecls : variableModifiers ! type ! formalParameterDeclsRest[$variableModifiers.tree, $type.tree] ;
    public final JavaParser.formalParameterDecls_return formalParameterDecls() throws RecognitionException {
        JavaParser.formalParameterDecls_return retval = new JavaParser.formalParameterDecls_return();
        retval.start = input.LT(1);

        int formalParameterDecls_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.variableModifiers_return variableModifiers242 =null;

        JavaParser.type_return type243 =null;

        JavaParser.formalParameterDeclsRest_return formalParameterDeclsRest244 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 61) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:536:5: ( variableModifiers ! type ! formalParameterDeclsRest[$variableModifiers.tree, $type.tree] )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:536:9: variableModifiers ! type ! formalParameterDeclsRest[$variableModifiers.tree, $type.tree]
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_variableModifiers_in_formalParameterDecls2912);
            variableModifiers242=variableModifiers();

            state._fsp--;
            if (state.failed) return retval;

            pushFollow(FOLLOW_type_in_formalParameterDecls2915);
            type243=type();

            state._fsp--;
            if (state.failed) return retval;

            pushFollow(FOLLOW_formalParameterDeclsRest_in_formalParameterDecls2918);
            formalParameterDeclsRest244=formalParameterDeclsRest((variableModifiers242!=null?((CommonTree)variableModifiers242.tree):null), (type243!=null?((CommonTree)type243.tree):null));

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameterDeclsRest244.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 61, formalParameterDecls_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "formalParameterDecls"


    public static class formalParameterDeclsRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "formalParameterDeclsRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:539:1: formalParameterDeclsRest[CommonTree modifiers, CommonTree type] : ( variableDeclaratorId ( ',' formalParameterDecls )? -> ^( PARAMETER variableDeclaratorId ) ( ',' formalParameterDecls )? | '...' variableDeclaratorId -> ^( PARAMETER '...' variableDeclaratorId ) );
    public final JavaParser.formalParameterDeclsRest_return formalParameterDeclsRest(CommonTree modifiers, CommonTree type) throws RecognitionException {
        JavaParser.formalParameterDeclsRest_return retval = new JavaParser.formalParameterDeclsRest_return();
        retval.start = input.LT(1);

        int formalParameterDeclsRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal246=null;
        Token string_literal248=null;
        JavaParser.variableDeclaratorId_return variableDeclaratorId245 =null;

        JavaParser.formalParameterDecls_return formalParameterDecls247 =null;

        JavaParser.variableDeclaratorId_return variableDeclaratorId249 =null;


        CommonTree char_literal246_tree=null;
        CommonTree string_literal248_tree=null;
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleSubtreeStream stream_variableDeclaratorId=new RewriteRuleSubtreeStream(adaptor,"rule variableDeclaratorId");
        RewriteRuleSubtreeStream stream_formalParameterDecls=new RewriteRuleSubtreeStream(adaptor,"rule formalParameterDecls");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 62) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:540:5: ( variableDeclaratorId ( ',' formalParameterDecls )? -> ^( PARAMETER variableDeclaratorId ) ( ',' formalParameterDecls )? | '...' variableDeclaratorId -> ^( PARAMETER '...' variableDeclaratorId ) )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==Identifier) ) {
                alt79=1;
            }
            else if ( (LA79_0==72) ) {
                alt79=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;

            }
            switch (alt79) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:540:9: variableDeclaratorId ( ',' formalParameterDecls )?
                    {
                    pushFollow(FOLLOW_variableDeclaratorId_in_formalParameterDeclsRest2943);
                    variableDeclaratorId245=variableDeclaratorId();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_variableDeclaratorId.add(variableDeclaratorId245.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:540:30: ( ',' formalParameterDecls )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==68) ) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:540:31: ',' formalParameterDecls
                            {
                            char_literal246=(Token)match(input,68,FOLLOW_68_in_formalParameterDeclsRest2946); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_68.add(char_literal246);


                            pushFollow(FOLLOW_formalParameterDecls_in_formalParameterDeclsRest2948);
                            formalParameterDecls247=formalParameterDecls();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_formalParameterDecls.add(formalParameterDecls247.getTree());

                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: 68, variableDeclaratorId, formalParameterDecls
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 540:58: -> ^( PARAMETER variableDeclaratorId ) ( ',' formalParameterDecls )?
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:540:61: ^( PARAMETER variableDeclaratorId )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(PARAMETER, "PARAMETER")
                        , root_1);

                        adaptor.addChild(root_1, modifiers);

                        adaptor.addChild(root_1, type);

                        adaptor.addChild(root_1, stream_variableDeclaratorId.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:540:116: ( ',' formalParameterDecls )?
                        if ( stream_68.hasNext()||stream_formalParameterDecls.hasNext() ) {
                            adaptor.addChild(root_0, 
                            stream_68.nextNode()
                            );

                            adaptor.addChild(root_0, stream_formalParameterDecls.nextTree());

                        }
                        stream_68.reset();
                        stream_formalParameterDecls.reset();

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:541:9: '...' variableDeclaratorId
                    {
                    string_literal248=(Token)match(input,72,FOLLOW_72_in_formalParameterDeclsRest2979); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_72.add(string_literal248);


                    pushFollow(FOLLOW_variableDeclaratorId_in_formalParameterDeclsRest2981);
                    variableDeclaratorId249=variableDeclaratorId();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_variableDeclaratorId.add(variableDeclaratorId249.getTree());

                    // AST REWRITE
                    // elements: variableDeclaratorId, 72
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 541:36: -> ^( PARAMETER '...' variableDeclaratorId )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:541:39: ^( PARAMETER '...' variableDeclaratorId )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(PARAMETER, "PARAMETER")
                        , root_1);

                        adaptor.addChild(root_1, modifiers);

                        adaptor.addChild(root_1, type);

                        adaptor.addChild(root_1, 
                        stream_72.nextNode()
                        );

                        adaptor.addChild(root_1, stream_variableDeclaratorId.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 62, formalParameterDeclsRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "formalParameterDeclsRest"


    public static class methodBody_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "methodBody"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:544:1: methodBody : block ;
    public final JavaParser.methodBody_return methodBody() throws RecognitionException {
        JavaParser.methodBody_return retval = new JavaParser.methodBody_return();
        retval.start = input.LT(1);

        int methodBody_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.block_return block250 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 63) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:545:5: ( block )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:545:9: block
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_block_in_methodBody3018);
            block250=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block250.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 63, methodBody_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "methodBody"


    public static class constructorBody_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constructorBody"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:548:1: constructorBody : '{' ( explicitConstructorInvocation )? ( blockStatement )* '}' ;
    public final JavaParser.constructorBody_return constructorBody() throws RecognitionException {
        JavaParser.constructorBody_return retval = new JavaParser.constructorBody_return();
        retval.start = input.LT(1);

        int constructorBody_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal251=null;
        Token char_literal254=null;
        JavaParser.explicitConstructorInvocation_return explicitConstructorInvocation252 =null;

        JavaParser.blockStatement_return blockStatement253 =null;


        CommonTree char_literal251_tree=null;
        CommonTree char_literal254_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 64) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:549:5: ( '{' ( explicitConstructorInvocation )? ( blockStatement )* '}' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:549:9: '{' ( explicitConstructorInvocation )? ( blockStatement )* '}'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal251=(Token)match(input,124,FOLLOW_124_in_constructorBody3037); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal251_tree = 
            (CommonTree)adaptor.create(char_literal251)
            ;
            adaptor.addChild(root_0, char_literal251_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:549:13: ( explicitConstructorInvocation )?
            int alt80=2;
            switch ( input.LA(1) ) {
                case LS:
                    {
                    alt80=1;
                    }
                    break;
                case 116:
                    {
                    int LA80_2 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case 62:
                    {
                    int LA80_3 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case SUPER:
                    {
                    int LA80_4 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case DecimalLiteral:
                case HexLiteral:
                case OctalLiteral:
                    {
                    int LA80_5 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case FloatingPointLiteral:
                    {
                    int LA80_6 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case CharacterLiteral:
                    {
                    int LA80_7 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case StringLiteral:
                    {
                    int LA80_8 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case 95:
                case 120:
                    {
                    int LA80_9 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case 107:
                    {
                    int LA80_10 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case 106:
                    {
                    int LA80_11 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case Identifier:
                    {
                    int LA80_12 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case 84:
                case 86:
                case 89:
                case 93:
                case 98:
                case 102:
                case 104:
                case 112:
                    {
                    int LA80_13 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
                case VOID:
                    {
                    int LA80_14 = input.LA(2);

                    if ( (synpred113_Java()) ) {
                        alt80=1;
                    }
                    }
                    break;
            }

            switch (alt80) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:549:13: explicitConstructorInvocation
                    {
                    pushFollow(FOLLOW_explicitConstructorInvocation_in_constructorBody3039);
                    explicitConstructorInvocation252=explicitConstructorInvocation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, explicitConstructorInvocation252.getTree());

                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:549:44: ( blockStatement )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==ASSERT||LA81_0==CLASS||LA81_0==CharacterLiteral||(LA81_0 >= DecimalLiteral && LA81_0 <= ENUM)||LA81_0==FloatingPointLiteral||LA81_0==HexLiteral||LA81_0==Identifier||LA81_0==OctalLiteral||LA81_0==SEMI||(LA81_0 >= STATIC && LA81_0 <= StringLiteral)||LA81_0==VOID||LA81_0==55||LA81_0==62||(LA81_0 >= 65 && LA81_0 <= 66)||(LA81_0 >= 69 && LA81_0 <= 70)||LA81_0==78||(LA81_0 >= 83 && LA81_0 <= 86)||(LA81_0 >= 89 && LA81_0 <= 90)||(LA81_0 >= 92 && LA81_0 <= 93)||(LA81_0 >= 95 && LA81_0 <= 96)||(LA81_0 >= 98 && LA81_0 <= 100)||(LA81_0 >= 102 && LA81_0 <= 104)||(LA81_0 >= 106 && LA81_0 <= 117)||(LA81_0 >= 120 && LA81_0 <= 121)||(LA81_0 >= 123 && LA81_0 <= 124)||LA81_0==129) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:549:44: blockStatement
            	    {
            	    pushFollow(FOLLOW_blockStatement_in_constructorBody3042);
            	    blockStatement253=blockStatement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, blockStatement253.getTree());

            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);


            char_literal254=(Token)match(input,128,FOLLOW_128_in_constructorBody3045); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal254_tree = 
            (CommonTree)adaptor.create(char_literal254)
            ;
            adaptor.addChild(root_0, char_literal254_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 64, constructorBody_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "constructorBody"


    public static class explicitConstructorInvocation_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "explicitConstructorInvocation"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:552:1: explicitConstructorInvocation : ( ( nonWildcardTypeArguments )? ( 'this' | SUPER ) arguments ';' | primary '.' ( nonWildcardTypeArguments )? SUPER arguments ';' );
    public final JavaParser.explicitConstructorInvocation_return explicitConstructorInvocation() throws RecognitionException {
        JavaParser.explicitConstructorInvocation_return retval = new JavaParser.explicitConstructorInvocation_return();
        retval.start = input.LT(1);

        int explicitConstructorInvocation_StartIndex = input.index();

        CommonTree root_0 = null;

        Token set256=null;
        Token char_literal258=null;
        Token char_literal260=null;
        Token SUPER262=null;
        Token char_literal264=null;
        JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments255 =null;

        JavaParser.arguments_return arguments257 =null;

        JavaParser.primary_return primary259 =null;

        JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments261 =null;

        JavaParser.arguments_return arguments263 =null;


        CommonTree set256_tree=null;
        CommonTree char_literal258_tree=null;
        CommonTree char_literal260_tree=null;
        CommonTree SUPER262_tree=null;
        CommonTree char_literal264_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 65) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:553:5: ( ( nonWildcardTypeArguments )? ( 'this' | SUPER ) arguments ';' | primary '.' ( nonWildcardTypeArguments )? SUPER arguments ';' )
            int alt84=2;
            switch ( input.LA(1) ) {
            case LS:
                {
                alt84=1;
                }
                break;
            case 116:
                {
                int LA84_2 = input.LA(2);

                if ( (synpred117_Java()) ) {
                    alt84=1;
                }
                else if ( (true) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 2, input);

                    throw nvae;

                }
                }
                break;
            case CharacterLiteral:
            case DecimalLiteral:
            case FloatingPointLiteral:
            case HexLiteral:
            case Identifier:
            case OctalLiteral:
            case StringLiteral:
            case VOID:
            case 62:
            case 84:
            case 86:
            case 89:
            case 93:
            case 95:
            case 98:
            case 102:
            case 104:
            case 106:
            case 107:
            case 112:
            case 120:
                {
                alt84=2;
                }
                break;
            case SUPER:
                {
                int LA84_4 = input.LA(2);

                if ( (synpred117_Java()) ) {
                    alt84=1;
                }
                else if ( (true) ) {
                    alt84=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 84, 4, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;

            }

            switch (alt84) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:553:9: ( nonWildcardTypeArguments )? ( 'this' | SUPER ) arguments ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:553:9: ( nonWildcardTypeArguments )?
                    int alt82=2;
                    int LA82_0 = input.LA(1);

                    if ( (LA82_0==LS) ) {
                        alt82=1;
                    }
                    switch (alt82) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:553:9: nonWildcardTypeArguments
                            {
                            pushFollow(FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation3064);
                            nonWildcardTypeArguments255=nonWildcardTypeArguments();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, nonWildcardTypeArguments255.getTree());

                            }
                            break;

                    }


                    set256=(Token)input.LT(1);

                    if ( input.LA(1)==SUPER||input.LA(1)==116 ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(set256)
                        );
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_arguments_in_explicitConstructorInvocation3075);
                    arguments257=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments257.getTree());

                    char_literal258=(Token)match(input,SEMI,FOLLOW_SEMI_in_explicitConstructorInvocation3077); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal258_tree = 
                    (CommonTree)adaptor.create(char_literal258)
                    ;
                    adaptor.addChild(root_0, char_literal258_tree);
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:554:9: primary '.' ( nonWildcardTypeArguments )? SUPER arguments ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_primary_in_explicitConstructorInvocation3087);
                    primary259=primary();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primary259.getTree());

                    char_literal260=(Token)match(input,DOT,FOLLOW_DOT_in_explicitConstructorInvocation3089); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal260_tree = 
                    (CommonTree)adaptor.create(char_literal260)
                    ;
                    adaptor.addChild(root_0, char_literal260_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:554:21: ( nonWildcardTypeArguments )?
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==LS) ) {
                        alt83=1;
                    }
                    switch (alt83) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:554:21: nonWildcardTypeArguments
                            {
                            pushFollow(FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation3091);
                            nonWildcardTypeArguments261=nonWildcardTypeArguments();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, nonWildcardTypeArguments261.getTree());

                            }
                            break;

                    }


                    SUPER262=(Token)match(input,SUPER,FOLLOW_SUPER_in_explicitConstructorInvocation3094); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SUPER262_tree = 
                    (CommonTree)adaptor.create(SUPER262)
                    ;
                    adaptor.addChild(root_0, SUPER262_tree);
                    }

                    pushFollow(FOLLOW_arguments_in_explicitConstructorInvocation3096);
                    arguments263=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments263.getTree());

                    char_literal264=(Token)match(input,SEMI,FOLLOW_SEMI_in_explicitConstructorInvocation3098); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal264_tree = 
                    (CommonTree)adaptor.create(char_literal264)
                    ;
                    adaptor.addChild(root_0, char_literal264_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 65, explicitConstructorInvocation_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "explicitConstructorInvocation"


    public static class qualifiedName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "qualifiedName"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:558:1: qualifiedName : ( Identifier -> Identifier ) ( DOT ident= Identifier -> ^( DOT $qualifiedName $ident) )* ;
    public final JavaParser.qualifiedName_return qualifiedName() throws RecognitionException {
        JavaParser.qualifiedName_return retval = new JavaParser.qualifiedName_return();
        retval.start = input.LT(1);

        int qualifiedName_StartIndex = input.index();

        CommonTree root_0 = null;

        Token ident=null;
        Token Identifier265=null;
        Token DOT266=null;

        CommonTree ident_tree=null;
        CommonTree Identifier265_tree=null;
        CommonTree DOT266_tree=null;
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 66) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:559:5: ( ( Identifier -> Identifier ) ( DOT ident= Identifier -> ^( DOT $qualifiedName $ident) )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:559:9: ( Identifier -> Identifier ) ( DOT ident= Identifier -> ^( DOT $qualifiedName $ident) )*
            {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:559:9: ( Identifier -> Identifier )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:559:10: Identifier
            {
            Identifier265=(Token)match(input,Identifier,FOLLOW_Identifier_in_qualifiedName3119); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_Identifier.add(Identifier265);


            // AST REWRITE
            // elements: Identifier
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 559:21: -> Identifier
            {
                adaptor.addChild(root_0, 
                stream_Identifier.nextNode()
                );

            }


            retval.tree = root_0;
            }

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:559:36: ( DOT ident= Identifier -> ^( DOT $qualifiedName $ident) )*
            loop85:
            do {
                int alt85=2;
                int LA85_0 = input.LA(1);

                if ( (LA85_0==DOT) ) {
                    int LA85_2 = input.LA(2);

                    if ( (LA85_2==Identifier) ) {
                        alt85=1;
                    }


                }


                switch (alt85) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:559:37: DOT ident= Identifier
            	    {
            	    DOT266=(Token)match(input,DOT,FOLLOW_DOT_in_qualifiedName3127); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_DOT.add(DOT266);


            	    ident=(Token)match(input,Identifier,FOLLOW_Identifier_in_qualifiedName3131); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_Identifier.add(ident);


            	    // AST REWRITE
            	    // elements: ident, DOT, qualifiedName
            	    // token labels: ident
            	    // rule labels: retval
            	    // token list labels: 
            	    // rule list labels: 
            	    // wildcard labels: 
            	    if ( state.backtracking==0 ) {

            	    retval.tree = root_0;
            	    RewriteRuleTokenStream stream_ident=new RewriteRuleTokenStream(adaptor,"token ident",ident);
            	    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            	    root_0 = (CommonTree)adaptor.nil();
            	    // 559:58: -> ^( DOT $qualifiedName $ident)
            	    {
            	        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:559:61: ^( DOT $qualifiedName $ident)
            	        {
            	        CommonTree root_1 = (CommonTree)adaptor.nil();
            	        root_1 = (CommonTree)adaptor.becomeRoot(
            	        stream_DOT.nextNode()
            	        , root_1);

            	        adaptor.addChild(root_1, stream_retval.nextTree());

            	        adaptor.addChild(root_1, stream_ident.nextNode());

            	        adaptor.addChild(root_0, root_1);
            	        }

            	    }


            	    retval.tree = root_0;
            	    }

            	    }
            	    break;

            	default :
            	    break loop85;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 66, qualifiedName_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "qualifiedName"


    public static class literal_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "literal"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:562:1: literal : ( integerLiteral | FloatingPointLiteral | CharacterLiteral | StringLiteral | booleanLiteral | 'null' );
    public final JavaParser.literal_return literal() throws RecognitionException {
        JavaParser.literal_return retval = new JavaParser.literal_return();
        retval.start = input.LT(1);

        int literal_StartIndex = input.index();

        CommonTree root_0 = null;

        Token FloatingPointLiteral268=null;
        Token CharacterLiteral269=null;
        Token StringLiteral270=null;
        Token string_literal272=null;
        JavaParser.integerLiteral_return integerLiteral267 =null;

        JavaParser.booleanLiteral_return booleanLiteral271 =null;


        CommonTree FloatingPointLiteral268_tree=null;
        CommonTree CharacterLiteral269_tree=null;
        CommonTree StringLiteral270_tree=null;
        CommonTree string_literal272_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 67) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:563:5: ( integerLiteral | FloatingPointLiteral | CharacterLiteral | StringLiteral | booleanLiteral | 'null' )
            int alt86=6;
            switch ( input.LA(1) ) {
            case DecimalLiteral:
            case HexLiteral:
            case OctalLiteral:
                {
                alt86=1;
                }
                break;
            case FloatingPointLiteral:
                {
                alt86=2;
                }
                break;
            case CharacterLiteral:
                {
                alt86=3;
                }
                break;
            case StringLiteral:
                {
                alt86=4;
                }
                break;
            case 95:
            case 120:
                {
                alt86=5;
                }
                break;
            case 107:
                {
                alt86=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;

            }

            switch (alt86) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:563:9: integerLiteral
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_integerLiteral_in_literal3169);
                    integerLiteral267=integerLiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, integerLiteral267.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:564:9: FloatingPointLiteral
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    FloatingPointLiteral268=(Token)match(input,FloatingPointLiteral,FOLLOW_FloatingPointLiteral_in_literal3179); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FloatingPointLiteral268_tree = 
                    (CommonTree)adaptor.create(FloatingPointLiteral268)
                    ;
                    adaptor.addChild(root_0, FloatingPointLiteral268_tree);
                    }

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:565:9: CharacterLiteral
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    CharacterLiteral269=(Token)match(input,CharacterLiteral,FOLLOW_CharacterLiteral_in_literal3189); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CharacterLiteral269_tree = 
                    (CommonTree)adaptor.create(CharacterLiteral269)
                    ;
                    adaptor.addChild(root_0, CharacterLiteral269_tree);
                    }

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:566:9: StringLiteral
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    StringLiteral270=(Token)match(input,StringLiteral,FOLLOW_StringLiteral_in_literal3199); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    StringLiteral270_tree = 
                    (CommonTree)adaptor.create(StringLiteral270)
                    ;
                    adaptor.addChild(root_0, StringLiteral270_tree);
                    }

                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:567:9: booleanLiteral
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_booleanLiteral_in_literal3209);
                    booleanLiteral271=booleanLiteral();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, booleanLiteral271.getTree());

                    }
                    break;
                case 6 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:568:9: 'null'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal272=(Token)match(input,107,FOLLOW_107_in_literal3219); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal272_tree = 
                    (CommonTree)adaptor.create(string_literal272)
                    ;
                    adaptor.addChild(root_0, string_literal272_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 67, literal_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "literal"


    public static class integerLiteral_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "integerLiteral"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:571:1: integerLiteral : ( HexLiteral | OctalLiteral | DecimalLiteral );
    public final JavaParser.integerLiteral_return integerLiteral() throws RecognitionException {
        JavaParser.integerLiteral_return retval = new JavaParser.integerLiteral_return();
        retval.start = input.LT(1);

        int integerLiteral_StartIndex = input.index();

        CommonTree root_0 = null;

        Token set273=null;

        CommonTree set273_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 68) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:572:5: ( HexLiteral | OctalLiteral | DecimalLiteral )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            set273=(Token)input.LT(1);

            if ( input.LA(1)==DecimalLiteral||input.LA(1)==HexLiteral||input.LA(1)==OctalLiteral ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set273)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 68, integerLiteral_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "integerLiteral"


    public static class booleanLiteral_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "booleanLiteral"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:577:1: booleanLiteral : ( 'true' | 'false' );
    public final JavaParser.booleanLiteral_return booleanLiteral() throws RecognitionException {
        JavaParser.booleanLiteral_return retval = new JavaParser.booleanLiteral_return();
        retval.start = input.LT(1);

        int booleanLiteral_StartIndex = input.index();

        CommonTree root_0 = null;

        Token set274=null;

        CommonTree set274_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 69) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:578:5: ( 'true' | 'false' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            {
            root_0 = (CommonTree)adaptor.nil();


            set274=(Token)input.LT(1);

            if ( input.LA(1)==95||input.LA(1)==120 ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                (CommonTree)adaptor.create(set274)
                );
                state.errorRecovery=false;
                state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 69, booleanLiteral_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "booleanLiteral"


    public static class annotations_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "annotations"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:584:1: annotations : ( annotation )+ ;
    public final JavaParser.annotations_return annotations() throws RecognitionException {
        JavaParser.annotations_return retval = new JavaParser.annotations_return();
        retval.start = input.LT(1);

        int annotations_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.annotation_return annotation275 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 70) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:585:5: ( ( annotation )+ )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:585:9: ( annotation )+
            {
            root_0 = (CommonTree)adaptor.nil();


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:585:9: ( annotation )+
            int cnt87=0;
            loop87:
            do {
                int alt87=2;
                int LA87_0 = input.LA(1);

                if ( (LA87_0==78) ) {
                    int LA87_2 = input.LA(2);

                    if ( (LA87_2==Identifier) ) {
                        int LA87_3 = input.LA(3);

                        if ( (synpred128_Java()) ) {
                            alt87=1;
                        }


                    }


                }


                switch (alt87) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:585:9: annotation
            	    {
            	    pushFollow(FOLLOW_annotation_in_annotations3308);
            	    annotation275=annotation();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation275.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt87 >= 1 ) break loop87;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(87, input);
                        throw eee;
                }
                cnt87++;
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 70, annotations_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "annotations"


    public static class annotation_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "annotation"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:588:1: annotation : '@' annotationName ( '(' ( elementValuePairs | elementValue )? ')' )? -> ^( ANNOTATION annotationName ) ;
    public final JavaParser.annotation_return annotation() throws RecognitionException {
        JavaParser.annotation_return retval = new JavaParser.annotation_return();
        retval.start = input.LT(1);

        int annotation_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal276=null;
        Token char_literal278=null;
        Token char_literal281=null;
        JavaParser.annotationName_return annotationName277 =null;

        JavaParser.elementValuePairs_return elementValuePairs279 =null;

        JavaParser.elementValue_return elementValue280 =null;


        CommonTree char_literal276_tree=null;
        CommonTree char_literal278_tree=null;
        CommonTree char_literal281_tree=null;
        RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleSubtreeStream stream_annotationName=new RewriteRuleSubtreeStream(adaptor,"rule annotationName");
        RewriteRuleSubtreeStream stream_elementValue=new RewriteRuleSubtreeStream(adaptor,"rule elementValue");
        RewriteRuleSubtreeStream stream_elementValuePairs=new RewriteRuleSubtreeStream(adaptor,"rule elementValuePairs");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 71) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:589:5: ( '@' annotationName ( '(' ( elementValuePairs | elementValue )? ')' )? -> ^( ANNOTATION annotationName ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:589:9: '@' annotationName ( '(' ( elementValuePairs | elementValue )? ')' )?
            {
            char_literal276=(Token)match(input,78,FOLLOW_78_in_annotation3329); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_78.add(char_literal276);


            pushFollow(FOLLOW_annotationName_in_annotation3331);
            annotationName277=annotationName();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_annotationName.add(annotationName277.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:589:28: ( '(' ( elementValuePairs | elementValue )? ')' )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==62) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:589:30: '(' ( elementValuePairs | elementValue )? ')'
                    {
                    char_literal278=(Token)match(input,62,FOLLOW_62_in_annotation3335); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_62.add(char_literal278);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:589:34: ( elementValuePairs | elementValue )?
                    int alt88=3;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==Identifier) ) {
                        int LA88_1 = input.LA(2);

                        if ( (LA88_1==EQ) ) {
                            alt88=1;
                        }
                        else if ( (LA88_1==DOT||LA88_1==GT||LA88_1==LS||LA88_1==STAR||(LA88_1 >= 56 && LA88_1 <= 57)||(LA88_1 >= 59 && LA88_1 <= 60)||(LA88_1 >= 62 && LA88_1 <= 63)||(LA88_1 >= 65 && LA88_1 <= 66)||(LA88_1 >= 69 && LA88_1 <= 70)||LA88_1==73||(LA88_1 >= 76 && LA88_1 <= 77)||LA88_1==79||LA88_1==81||LA88_1==101||LA88_1==125||LA88_1==127) ) {
                            alt88=2;
                        }
                    }
                    else if ( (LA88_0==CharacterLiteral||LA88_0==DecimalLiteral||LA88_0==FloatingPointLiteral||LA88_0==HexLiteral||LA88_0==OctalLiteral||(LA88_0 >= SUPER && LA88_0 <= StringLiteral)||LA88_0==VOID||LA88_0==55||LA88_0==62||(LA88_0 >= 65 && LA88_0 <= 66)||(LA88_0 >= 69 && LA88_0 <= 70)||LA88_0==78||LA88_0==84||LA88_0==86||LA88_0==89||LA88_0==93||LA88_0==95||LA88_0==98||LA88_0==102||LA88_0==104||(LA88_0 >= 106 && LA88_0 <= 107)||LA88_0==112||LA88_0==116||LA88_0==120||LA88_0==124||LA88_0==129) ) {
                        alt88=2;
                    }
                    switch (alt88) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:589:36: elementValuePairs
                            {
                            pushFollow(FOLLOW_elementValuePairs_in_annotation3339);
                            elementValuePairs279=elementValuePairs();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_elementValuePairs.add(elementValuePairs279.getTree());

                            }
                            break;
                        case 2 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:589:56: elementValue
                            {
                            pushFollow(FOLLOW_elementValue_in_annotation3343);
                            elementValue280=elementValue();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_elementValue.add(elementValue280.getTree());

                            }
                            break;

                    }


                    char_literal281=(Token)match(input,63,FOLLOW_63_in_annotation3348); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_63.add(char_literal281);


                    }
                    break;

            }


            // AST REWRITE
            // elements: annotationName
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 589:79: -> ^( ANNOTATION annotationName )
            {
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:589:82: ^( ANNOTATION annotationName )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(ANNOTATION, "ANNOTATION")
                , root_1);

                adaptor.addChild(root_1, stream_annotationName.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 71, annotation_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "annotation"


    public static class annotationName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "annotationName"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:592:1: annotationName : Identifier ( '.' Identifier )* ;
    public final JavaParser.annotationName_return annotationName() throws RecognitionException {
        JavaParser.annotationName_return retval = new JavaParser.annotationName_return();
        retval.start = input.LT(1);

        int annotationName_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier282=null;
        Token char_literal283=null;
        Token Identifier284=null;

        CommonTree Identifier282_tree=null;
        CommonTree char_literal283_tree=null;
        CommonTree Identifier284_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 72) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:593:5: ( Identifier ( '.' Identifier )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:593:7: Identifier ( '.' Identifier )*
            {
            root_0 = (CommonTree)adaptor.nil();


            Identifier282=(Token)match(input,Identifier,FOLLOW_Identifier_in_annotationName3381); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier282_tree = 
            (CommonTree)adaptor.create(Identifier282)
            ;
            adaptor.addChild(root_0, Identifier282_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:593:18: ( '.' Identifier )*
            loop90:
            do {
                int alt90=2;
                int LA90_0 = input.LA(1);

                if ( (LA90_0==DOT) ) {
                    alt90=1;
                }


                switch (alt90) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:593:19: '.' Identifier
            	    {
            	    char_literal283=(Token)match(input,DOT,FOLLOW_DOT_in_annotationName3384); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal283_tree = 
            	    (CommonTree)adaptor.create(char_literal283)
            	    ;
            	    adaptor.addChild(root_0, char_literal283_tree);
            	    }

            	    Identifier284=(Token)match(input,Identifier,FOLLOW_Identifier_in_annotationName3386); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    Identifier284_tree = 
            	    (CommonTree)adaptor.create(Identifier284)
            	    ;
            	    adaptor.addChild(root_0, Identifier284_tree);
            	    }

            	    }
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 72, annotationName_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "annotationName"


    public static class elementValuePairs_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "elementValuePairs"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:596:1: elementValuePairs : elementValuePair ( ',' elementValuePair )* ;
    public final JavaParser.elementValuePairs_return elementValuePairs() throws RecognitionException {
        JavaParser.elementValuePairs_return retval = new JavaParser.elementValuePairs_return();
        retval.start = input.LT(1);

        int elementValuePairs_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal286=null;
        JavaParser.elementValuePair_return elementValuePair285 =null;

        JavaParser.elementValuePair_return elementValuePair287 =null;


        CommonTree char_literal286_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 73) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:597:5: ( elementValuePair ( ',' elementValuePair )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:597:9: elementValuePair ( ',' elementValuePair )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_elementValuePair_in_elementValuePairs3407);
            elementValuePair285=elementValuePair();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValuePair285.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:597:26: ( ',' elementValuePair )*
            loop91:
            do {
                int alt91=2;
                int LA91_0 = input.LA(1);

                if ( (LA91_0==68) ) {
                    alt91=1;
                }


                switch (alt91) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:597:27: ',' elementValuePair
            	    {
            	    char_literal286=(Token)match(input,68,FOLLOW_68_in_elementValuePairs3410); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal286_tree = 
            	    (CommonTree)adaptor.create(char_literal286)
            	    ;
            	    adaptor.addChild(root_0, char_literal286_tree);
            	    }

            	    pushFollow(FOLLOW_elementValuePair_in_elementValuePairs3412);
            	    elementValuePair287=elementValuePair();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValuePair287.getTree());

            	    }
            	    break;

            	default :
            	    break loop91;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 73, elementValuePairs_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "elementValuePairs"


    public static class elementValuePair_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "elementValuePair"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:600:1: elementValuePair : Identifier '=' elementValue ;
    public final JavaParser.elementValuePair_return elementValuePair() throws RecognitionException {
        JavaParser.elementValuePair_return retval = new JavaParser.elementValuePair_return();
        retval.start = input.LT(1);

        int elementValuePair_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier288=null;
        Token char_literal289=null;
        JavaParser.elementValue_return elementValue290 =null;


        CommonTree Identifier288_tree=null;
        CommonTree char_literal289_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 74) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:601:5: ( Identifier '=' elementValue )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:601:9: Identifier '=' elementValue
            {
            root_0 = (CommonTree)adaptor.nil();


            Identifier288=(Token)match(input,Identifier,FOLLOW_Identifier_in_elementValuePair3433); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier288_tree = 
            (CommonTree)adaptor.create(Identifier288)
            ;
            adaptor.addChild(root_0, Identifier288_tree);
            }

            char_literal289=(Token)match(input,EQ,FOLLOW_EQ_in_elementValuePair3435); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal289_tree = 
            (CommonTree)adaptor.create(char_literal289)
            ;
            adaptor.addChild(root_0, char_literal289_tree);
            }

            pushFollow(FOLLOW_elementValue_in_elementValuePair3437);
            elementValue290=elementValue();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValue290.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 74, elementValuePair_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "elementValuePair"


    public static class elementValue_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "elementValue"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:604:1: elementValue : ( conditionalExpression | annotation | elementValueArrayInitializer );
    public final JavaParser.elementValue_return elementValue() throws RecognitionException {
        JavaParser.elementValue_return retval = new JavaParser.elementValue_return();
        retval.start = input.LT(1);

        int elementValue_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.conditionalExpression_return conditionalExpression291 =null;

        JavaParser.annotation_return annotation292 =null;

        JavaParser.elementValueArrayInitializer_return elementValueArrayInitializer293 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 75) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:605:5: ( conditionalExpression | annotation | elementValueArrayInitializer )
            int alt92=3;
            switch ( input.LA(1) ) {
            case CharacterLiteral:
            case DecimalLiteral:
            case FloatingPointLiteral:
            case HexLiteral:
            case Identifier:
            case OctalLiteral:
            case SUPER:
            case StringLiteral:
            case VOID:
            case 55:
            case 62:
            case 65:
            case 66:
            case 69:
            case 70:
            case 84:
            case 86:
            case 89:
            case 93:
            case 95:
            case 98:
            case 102:
            case 104:
            case 106:
            case 107:
            case 112:
            case 116:
            case 120:
            case 129:
                {
                alt92=1;
                }
                break;
            case 78:
                {
                alt92=2;
                }
                break;
            case 124:
                {
                alt92=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 92, 0, input);

                throw nvae;

            }

            switch (alt92) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:605:9: conditionalExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_conditionalExpression_in_elementValue3460);
                    conditionalExpression291=conditionalExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression291.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:606:9: annotation
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_annotation_in_elementValue3470);
                    annotation292=annotation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotation292.getTree());

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:607:9: elementValueArrayInitializer
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_elementValueArrayInitializer_in_elementValue3480);
                    elementValueArrayInitializer293=elementValueArrayInitializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValueArrayInitializer293.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 75, elementValue_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "elementValue"


    public static class elementValueArrayInitializer_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "elementValueArrayInitializer"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:610:1: elementValueArrayInitializer : '{' ( elementValue ( ',' elementValue )* )? ( ',' )? '}' ;
    public final JavaParser.elementValueArrayInitializer_return elementValueArrayInitializer() throws RecognitionException {
        JavaParser.elementValueArrayInitializer_return retval = new JavaParser.elementValueArrayInitializer_return();
        retval.start = input.LT(1);

        int elementValueArrayInitializer_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal294=null;
        Token char_literal296=null;
        Token char_literal298=null;
        Token char_literal299=null;
        JavaParser.elementValue_return elementValue295 =null;

        JavaParser.elementValue_return elementValue297 =null;


        CommonTree char_literal294_tree=null;
        CommonTree char_literal296_tree=null;
        CommonTree char_literal298_tree=null;
        CommonTree char_literal299_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 76) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:611:5: ( '{' ( elementValue ( ',' elementValue )* )? ( ',' )? '}' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:611:9: '{' ( elementValue ( ',' elementValue )* )? ( ',' )? '}'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal294=(Token)match(input,124,FOLLOW_124_in_elementValueArrayInitializer3503); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal294_tree = 
            (CommonTree)adaptor.create(char_literal294)
            ;
            adaptor.addChild(root_0, char_literal294_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:611:13: ( elementValue ( ',' elementValue )* )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==CharacterLiteral||LA94_0==DecimalLiteral||LA94_0==FloatingPointLiteral||LA94_0==HexLiteral||LA94_0==Identifier||LA94_0==OctalLiteral||(LA94_0 >= SUPER && LA94_0 <= StringLiteral)||LA94_0==VOID||LA94_0==55||LA94_0==62||(LA94_0 >= 65 && LA94_0 <= 66)||(LA94_0 >= 69 && LA94_0 <= 70)||LA94_0==78||LA94_0==84||LA94_0==86||LA94_0==89||LA94_0==93||LA94_0==95||LA94_0==98||LA94_0==102||LA94_0==104||(LA94_0 >= 106 && LA94_0 <= 107)||LA94_0==112||LA94_0==116||LA94_0==120||LA94_0==124||LA94_0==129) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:611:14: elementValue ( ',' elementValue )*
                    {
                    pushFollow(FOLLOW_elementValue_in_elementValueArrayInitializer3506);
                    elementValue295=elementValue();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValue295.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:611:27: ( ',' elementValue )*
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==68) ) {
                            int LA93_1 = input.LA(2);

                            if ( (LA93_1==CharacterLiteral||LA93_1==DecimalLiteral||LA93_1==FloatingPointLiteral||LA93_1==HexLiteral||LA93_1==Identifier||LA93_1==OctalLiteral||(LA93_1 >= SUPER && LA93_1 <= StringLiteral)||LA93_1==VOID||LA93_1==55||LA93_1==62||(LA93_1 >= 65 && LA93_1 <= 66)||(LA93_1 >= 69 && LA93_1 <= 70)||LA93_1==78||LA93_1==84||LA93_1==86||LA93_1==89||LA93_1==93||LA93_1==95||LA93_1==98||LA93_1==102||LA93_1==104||(LA93_1 >= 106 && LA93_1 <= 107)||LA93_1==112||LA93_1==116||LA93_1==120||LA93_1==124||LA93_1==129) ) {
                                alt93=1;
                            }


                        }


                        switch (alt93) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:611:28: ',' elementValue
                    	    {
                    	    char_literal296=(Token)match(input,68,FOLLOW_68_in_elementValueArrayInitializer3509); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal296_tree = 
                    	    (CommonTree)adaptor.create(char_literal296)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal296_tree);
                    	    }

                    	    pushFollow(FOLLOW_elementValue_in_elementValueArrayInitializer3511);
                    	    elementValue297=elementValue();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValue297.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop93;
                        }
                    } while (true);


                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:611:49: ( ',' )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==68) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:611:50: ','
                    {
                    char_literal298=(Token)match(input,68,FOLLOW_68_in_elementValueArrayInitializer3518); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal298_tree = 
                    (CommonTree)adaptor.create(char_literal298)
                    ;
                    adaptor.addChild(root_0, char_literal298_tree);
                    }

                    }
                    break;

            }


            char_literal299=(Token)match(input,128,FOLLOW_128_in_elementValueArrayInitializer3522); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal299_tree = 
            (CommonTree)adaptor.create(char_literal299)
            ;
            adaptor.addChild(root_0, char_literal299_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 76, elementValueArrayInitializer_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "elementValueArrayInitializer"


    public static class annotationTypeDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "annotationTypeDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:614:1: annotationTypeDeclaration[CommonTree modifiers] : '@' 'interface' Identifier annotationTypeBody ;
    public final JavaParser.annotationTypeDeclaration_return annotationTypeDeclaration(CommonTree modifiers) throws RecognitionException {
        JavaParser.annotationTypeDeclaration_return retval = new JavaParser.annotationTypeDeclaration_return();
        retval.start = input.LT(1);

        int annotationTypeDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal300=null;
        Token string_literal301=null;
        Token Identifier302=null;
        JavaParser.annotationTypeBody_return annotationTypeBody303 =null;


        CommonTree char_literal300_tree=null;
        CommonTree string_literal301_tree=null;
        CommonTree Identifier302_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 77) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:615:5: ( '@' 'interface' Identifier annotationTypeBody )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:615:9: '@' 'interface' Identifier annotationTypeBody
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal300=(Token)match(input,78,FOLLOW_78_in_annotationTypeDeclaration3547); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal300_tree = 
            (CommonTree)adaptor.create(char_literal300)
            ;
            adaptor.addChild(root_0, char_literal300_tree);
            }

            string_literal301=(Token)match(input,103,FOLLOW_103_in_annotationTypeDeclaration3549); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal301_tree = 
            (CommonTree)adaptor.create(string_literal301)
            ;
            adaptor.addChild(root_0, string_literal301_tree);
            }

            Identifier302=(Token)match(input,Identifier,FOLLOW_Identifier_in_annotationTypeDeclaration3551); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier302_tree = 
            (CommonTree)adaptor.create(Identifier302)
            ;
            adaptor.addChild(root_0, Identifier302_tree);
            }

            pushFollow(FOLLOW_annotationTypeBody_in_annotationTypeDeclaration3553);
            annotationTypeBody303=annotationTypeBody();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationTypeBody303.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 77, annotationTypeDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "annotationTypeDeclaration"


    public static class annotationTypeBody_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "annotationTypeBody"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:618:1: annotationTypeBody : '{' ( annotationTypeElementDeclaration )* '}' ;
    public final JavaParser.annotationTypeBody_return annotationTypeBody() throws RecognitionException {
        JavaParser.annotationTypeBody_return retval = new JavaParser.annotationTypeBody_return();
        retval.start = input.LT(1);

        int annotationTypeBody_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal304=null;
        Token char_literal306=null;
        JavaParser.annotationTypeElementDeclaration_return annotationTypeElementDeclaration305 =null;


        CommonTree char_literal304_tree=null;
        CommonTree char_literal306_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 78) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:619:5: ( '{' ( annotationTypeElementDeclaration )* '}' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:619:9: '{' ( annotationTypeElementDeclaration )* '}'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal304=(Token)match(input,124,FOLLOW_124_in_annotationTypeBody3576); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal304_tree = 
            (CommonTree)adaptor.create(char_literal304)
            ;
            adaptor.addChild(root_0, char_literal304_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:619:13: ( annotationTypeElementDeclaration )*
            loop96:
            do {
                int alt96=2;
                int LA96_0 = input.LA(1);

                if ( (LA96_0==CLASS||LA96_0==ENUM||LA96_0==Identifier||LA96_0==LS||LA96_0==STATIC||LA96_0==VOID||LA96_0==78||(LA96_0 >= 83 && LA96_0 <= 84)||LA96_0==86||LA96_0==89||LA96_0==93||LA96_0==96||LA96_0==98||(LA96_0 >= 102 && LA96_0 <= 105)||(LA96_0 >= 108 && LA96_0 <= 110)||(LA96_0 >= 112 && LA96_0 <= 113)||LA96_0==115||LA96_0==119||LA96_0==122) ) {
                    alt96=1;
                }


                switch (alt96) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:619:14: annotationTypeElementDeclaration
            	    {
            	    pushFollow(FOLLOW_annotationTypeElementDeclaration_in_annotationTypeBody3579);
            	    annotationTypeElementDeclaration305=annotationTypeElementDeclaration();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationTypeElementDeclaration305.getTree());

            	    }
            	    break;

            	default :
            	    break loop96;
                }
            } while (true);


            char_literal306=(Token)match(input,128,FOLLOW_128_in_annotationTypeBody3583); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal306_tree = 
            (CommonTree)adaptor.create(char_literal306)
            ;
            adaptor.addChild(root_0, char_literal306_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 78, annotationTypeBody_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "annotationTypeBody"


    public static class annotationTypeElementDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "annotationTypeElementDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:622:1: annotationTypeElementDeclaration : modifiers annotationTypeElementRest[$modifiers.tree] ;
    public final JavaParser.annotationTypeElementDeclaration_return annotationTypeElementDeclaration() throws RecognitionException {
        JavaParser.annotationTypeElementDeclaration_return retval = new JavaParser.annotationTypeElementDeclaration_return();
        retval.start = input.LT(1);

        int annotationTypeElementDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.modifiers_return modifiers307 =null;

        JavaParser.annotationTypeElementRest_return annotationTypeElementRest308 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 79) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:623:5: ( modifiers annotationTypeElementRest[$modifiers.tree] )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:623:9: modifiers annotationTypeElementRest[$modifiers.tree]
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_modifiers_in_annotationTypeElementDeclaration3606);
            modifiers307=modifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, modifiers307.getTree());

            pushFollow(FOLLOW_annotationTypeElementRest_in_annotationTypeElementDeclaration3608);
            annotationTypeElementRest308=annotationTypeElementRest((modifiers307!=null?((CommonTree)modifiers307.tree):null));

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationTypeElementRest308.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 79, annotationTypeElementDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "annotationTypeElementDeclaration"


    public static class annotationTypeElementRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "annotationTypeElementRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:626:1: annotationTypeElementRest[CommonTree modifiers] : ( type annotationMethodOrConstantRest ';' | normalClassDeclaration[$modifiers] ( ';' )? | normalInterfaceDeclaration[$modifiers] ( ';' )? | enumDeclaration[$modifiers] ( ';' )? | annotationTypeDeclaration[$modifiers] ( ';' )? );
    public final JavaParser.annotationTypeElementRest_return annotationTypeElementRest(CommonTree modifiers) throws RecognitionException {
        JavaParser.annotationTypeElementRest_return retval = new JavaParser.annotationTypeElementRest_return();
        retval.start = input.LT(1);

        int annotationTypeElementRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal311=null;
        Token char_literal313=null;
        Token char_literal315=null;
        Token char_literal317=null;
        Token char_literal319=null;
        JavaParser.type_return type309 =null;

        JavaParser.annotationMethodOrConstantRest_return annotationMethodOrConstantRest310 =null;

        JavaParser.normalClassDeclaration_return normalClassDeclaration312 =null;

        JavaParser.normalInterfaceDeclaration_return normalInterfaceDeclaration314 =null;

        JavaParser.enumDeclaration_return enumDeclaration316 =null;

        JavaParser.annotationTypeDeclaration_return annotationTypeDeclaration318 =null;


        CommonTree char_literal311_tree=null;
        CommonTree char_literal313_tree=null;
        CommonTree char_literal315_tree=null;
        CommonTree char_literal317_tree=null;
        CommonTree char_literal319_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 80) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:627:5: ( type annotationMethodOrConstantRest ';' | normalClassDeclaration[$modifiers] ( ';' )? | normalInterfaceDeclaration[$modifiers] ( ';' )? | enumDeclaration[$modifiers] ( ';' )? | annotationTypeDeclaration[$modifiers] ( ';' )? )
            int alt101=5;
            switch ( input.LA(1) ) {
            case Identifier:
            case 84:
            case 86:
            case 89:
            case 93:
            case 98:
            case 102:
            case 104:
            case 112:
                {
                alt101=1;
                }
                break;
            case CLASS:
                {
                alt101=2;
                }
                break;
            case 103:
                {
                alt101=3;
                }
                break;
            case ENUM:
                {
                alt101=4;
                }
                break;
            case 78:
                {
                alt101=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;

            }

            switch (alt101) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:627:9: type annotationMethodOrConstantRest ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_type_in_annotationTypeElementRest3633);
                    type309=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type309.getTree());

                    pushFollow(FOLLOW_annotationMethodOrConstantRest_in_annotationTypeElementRest3635);
                    annotationMethodOrConstantRest310=annotationMethodOrConstantRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationMethodOrConstantRest310.getTree());

                    char_literal311=(Token)match(input,SEMI,FOLLOW_SEMI_in_annotationTypeElementRest3637); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal311_tree = 
                    (CommonTree)adaptor.create(char_literal311)
                    ;
                    adaptor.addChild(root_0, char_literal311_tree);
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:628:9: normalClassDeclaration[$modifiers] ( ';' )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_normalClassDeclaration_in_annotationTypeElementRest3647);
                    normalClassDeclaration312=normalClassDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, normalClassDeclaration312.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:628:44: ( ';' )?
                    int alt97=2;
                    int LA97_0 = input.LA(1);

                    if ( (LA97_0==SEMI) ) {
                        alt97=1;
                    }
                    switch (alt97) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:628:44: ';'
                            {
                            char_literal313=(Token)match(input,SEMI,FOLLOW_SEMI_in_annotationTypeElementRest3650); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal313_tree = 
                            (CommonTree)adaptor.create(char_literal313)
                            ;
                            adaptor.addChild(root_0, char_literal313_tree);
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:629:9: normalInterfaceDeclaration[$modifiers] ( ';' )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_normalInterfaceDeclaration_in_annotationTypeElementRest3661);
                    normalInterfaceDeclaration314=normalInterfaceDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, normalInterfaceDeclaration314.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:629:48: ( ';' )?
                    int alt98=2;
                    int LA98_0 = input.LA(1);

                    if ( (LA98_0==SEMI) ) {
                        alt98=1;
                    }
                    switch (alt98) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:629:48: ';'
                            {
                            char_literal315=(Token)match(input,SEMI,FOLLOW_SEMI_in_annotationTypeElementRest3664); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal315_tree = 
                            (CommonTree)adaptor.create(char_literal315)
                            ;
                            adaptor.addChild(root_0, char_literal315_tree);
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:630:9: enumDeclaration[$modifiers] ( ';' )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_enumDeclaration_in_annotationTypeElementRest3675);
                    enumDeclaration316=enumDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumDeclaration316.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:630:37: ( ';' )?
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==SEMI) ) {
                        alt99=1;
                    }
                    switch (alt99) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:630:37: ';'
                            {
                            char_literal317=(Token)match(input,SEMI,FOLLOW_SEMI_in_annotationTypeElementRest3678); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal317_tree = 
                            (CommonTree)adaptor.create(char_literal317)
                            ;
                            adaptor.addChild(root_0, char_literal317_tree);
                            }

                            }
                            break;

                    }


                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:631:9: annotationTypeDeclaration[$modifiers] ( ';' )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_annotationTypeDeclaration_in_annotationTypeElementRest3689);
                    annotationTypeDeclaration318=annotationTypeDeclaration(modifiers);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationTypeDeclaration318.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:631:47: ( ';' )?
                    int alt100=2;
                    int LA100_0 = input.LA(1);

                    if ( (LA100_0==SEMI) ) {
                        alt100=1;
                    }
                    switch (alt100) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:631:47: ';'
                            {
                            char_literal319=(Token)match(input,SEMI,FOLLOW_SEMI_in_annotationTypeElementRest3692); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal319_tree = 
                            (CommonTree)adaptor.create(char_literal319)
                            ;
                            adaptor.addChild(root_0, char_literal319_tree);
                            }

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 80, annotationTypeElementRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "annotationTypeElementRest"


    public static class annotationMethodOrConstantRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "annotationMethodOrConstantRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:634:1: annotationMethodOrConstantRest : ( annotationMethodRest | annotationConstantRest );
    public final JavaParser.annotationMethodOrConstantRest_return annotationMethodOrConstantRest() throws RecognitionException {
        JavaParser.annotationMethodOrConstantRest_return retval = new JavaParser.annotationMethodOrConstantRest_return();
        retval.start = input.LT(1);

        int annotationMethodOrConstantRest_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.annotationMethodRest_return annotationMethodRest320 =null;

        JavaParser.annotationConstantRest_return annotationConstantRest321 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 81) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:635:5: ( annotationMethodRest | annotationConstantRest )
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==Identifier) ) {
                int LA102_1 = input.LA(2);

                if ( (LA102_1==62) ) {
                    alt102=1;
                }
                else if ( (LA102_1==EQ||LA102_1==SEMI||LA102_1==68||LA102_1==79) ) {
                    alt102=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 102, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 102, 0, input);

                throw nvae;

            }
            switch (alt102) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:635:9: annotationMethodRest
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_annotationMethodRest_in_annotationMethodOrConstantRest3716);
                    annotationMethodRest320=annotationMethodRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationMethodRest320.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:636:9: annotationConstantRest
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_annotationConstantRest_in_annotationMethodOrConstantRest3726);
                    annotationConstantRest321=annotationConstantRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, annotationConstantRest321.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 81, annotationMethodOrConstantRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "annotationMethodOrConstantRest"


    public static class annotationMethodRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "annotationMethodRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:639:1: annotationMethodRest : Identifier '(' ')' ( defaultValue )? ;
    public final JavaParser.annotationMethodRest_return annotationMethodRest() throws RecognitionException {
        JavaParser.annotationMethodRest_return retval = new JavaParser.annotationMethodRest_return();
        retval.start = input.LT(1);

        int annotationMethodRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier322=null;
        Token char_literal323=null;
        Token char_literal324=null;
        JavaParser.defaultValue_return defaultValue325 =null;


        CommonTree Identifier322_tree=null;
        CommonTree char_literal323_tree=null;
        CommonTree char_literal324_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 82) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:640:5: ( Identifier '(' ')' ( defaultValue )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:640:9: Identifier '(' ')' ( defaultValue )?
            {
            root_0 = (CommonTree)adaptor.nil();


            Identifier322=(Token)match(input,Identifier,FOLLOW_Identifier_in_annotationMethodRest3749); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier322_tree = 
            (CommonTree)adaptor.create(Identifier322)
            ;
            adaptor.addChild(root_0, Identifier322_tree);
            }

            char_literal323=(Token)match(input,62,FOLLOW_62_in_annotationMethodRest3751); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal323_tree = 
            (CommonTree)adaptor.create(char_literal323)
            ;
            adaptor.addChild(root_0, char_literal323_tree);
            }

            char_literal324=(Token)match(input,63,FOLLOW_63_in_annotationMethodRest3753); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal324_tree = 
            (CommonTree)adaptor.create(char_literal324)
            ;
            adaptor.addChild(root_0, char_literal324_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:640:28: ( defaultValue )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==91) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:640:28: defaultValue
                    {
                    pushFollow(FOLLOW_defaultValue_in_annotationMethodRest3755);
                    defaultValue325=defaultValue();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, defaultValue325.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 82, annotationMethodRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "annotationMethodRest"


    public static class annotationConstantRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "annotationConstantRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:643:1: annotationConstantRest : variableDeclarators ;
    public final JavaParser.annotationConstantRest_return annotationConstantRest() throws RecognitionException {
        JavaParser.annotationConstantRest_return retval = new JavaParser.annotationConstantRest_return();
        retval.start = input.LT(1);

        int annotationConstantRest_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.variableDeclarators_return variableDeclarators326 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 83) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:644:5: ( variableDeclarators )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:644:9: variableDeclarators
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_variableDeclarators_in_annotationConstantRest3779);
            variableDeclarators326=variableDeclarators();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableDeclarators326.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 83, annotationConstantRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "annotationConstantRest"


    public static class defaultValue_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "defaultValue"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:647:1: defaultValue : 'default' elementValue ;
    public final JavaParser.defaultValue_return defaultValue() throws RecognitionException {
        JavaParser.defaultValue_return retval = new JavaParser.defaultValue_return();
        retval.start = input.LT(1);

        int defaultValue_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal327=null;
        JavaParser.elementValue_return elementValue328 =null;


        CommonTree string_literal327_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 84) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:648:5: ( 'default' elementValue )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:648:9: 'default' elementValue
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal327=(Token)match(input,91,FOLLOW_91_in_defaultValue3802); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal327_tree = 
            (CommonTree)adaptor.create(string_literal327)
            ;
            adaptor.addChild(root_0, string_literal327_tree);
            }

            pushFollow(FOLLOW_elementValue_in_defaultValue3804);
            elementValue328=elementValue();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementValue328.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 84, defaultValue_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "defaultValue"


    public static class block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "block"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:653:1: block : '{' ( blockStatement )* '}' -> ^( BLOCK ( blockStatement )* ) ;
    public final JavaParser.block_return block() throws RecognitionException {
        JavaParser.block_return retval = new JavaParser.block_return();
        retval.start = input.LT(1);

        int block_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal329=null;
        Token char_literal331=null;
        JavaParser.blockStatement_return blockStatement330 =null;


        CommonTree char_literal329_tree=null;
        CommonTree char_literal331_tree=null;
        RewriteRuleTokenStream stream_128=new RewriteRuleTokenStream(adaptor,"token 128");
        RewriteRuleTokenStream stream_124=new RewriteRuleTokenStream(adaptor,"token 124");
        RewriteRuleSubtreeStream stream_blockStatement=new RewriteRuleSubtreeStream(adaptor,"rule blockStatement");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 85) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:654:5: ( '{' ( blockStatement )* '}' -> ^( BLOCK ( blockStatement )* ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:654:9: '{' ( blockStatement )* '}'
            {
            char_literal329=(Token)match(input,124,FOLLOW_124_in_block3825); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_124.add(char_literal329);


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:654:13: ( blockStatement )*
            loop104:
            do {
                int alt104=2;
                int LA104_0 = input.LA(1);

                if ( (LA104_0==ASSERT||LA104_0==CLASS||LA104_0==CharacterLiteral||(LA104_0 >= DecimalLiteral && LA104_0 <= ENUM)||LA104_0==FloatingPointLiteral||LA104_0==HexLiteral||LA104_0==Identifier||LA104_0==OctalLiteral||LA104_0==SEMI||(LA104_0 >= STATIC && LA104_0 <= StringLiteral)||LA104_0==VOID||LA104_0==55||LA104_0==62||(LA104_0 >= 65 && LA104_0 <= 66)||(LA104_0 >= 69 && LA104_0 <= 70)||LA104_0==78||(LA104_0 >= 83 && LA104_0 <= 86)||(LA104_0 >= 89 && LA104_0 <= 90)||(LA104_0 >= 92 && LA104_0 <= 93)||(LA104_0 >= 95 && LA104_0 <= 96)||(LA104_0 >= 98 && LA104_0 <= 100)||(LA104_0 >= 102 && LA104_0 <= 104)||(LA104_0 >= 106 && LA104_0 <= 117)||(LA104_0 >= 120 && LA104_0 <= 121)||(LA104_0 >= 123 && LA104_0 <= 124)||LA104_0==129) ) {
                    alt104=1;
                }


                switch (alt104) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:654:13: blockStatement
            	    {
            	    pushFollow(FOLLOW_blockStatement_in_block3827);
            	    blockStatement330=blockStatement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_blockStatement.add(blockStatement330.getTree());

            	    }
            	    break;

            	default :
            	    break loop104;
                }
            } while (true);


            char_literal331=(Token)match(input,128,FOLLOW_128_in_block3830); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_128.add(char_literal331);


            // AST REWRITE
            // elements: blockStatement
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 654:33: -> ^( BLOCK ( blockStatement )* )
            {
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:654:36: ^( BLOCK ( blockStatement )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(BLOCK, "BLOCK")
                , root_1);

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:654:44: ( blockStatement )*
                while ( stream_blockStatement.hasNext() ) {
                    adaptor.addChild(root_1, stream_blockStatement.nextTree());

                }
                stream_blockStatement.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 85, block_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "block"


    public static class blockStatement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "blockStatement"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:657:1: blockStatement : ( localVariableDeclarationStatement | classOrInterfaceDeclaration | statement );
    public final JavaParser.blockStatement_return blockStatement() throws RecognitionException {
        JavaParser.blockStatement_return retval = new JavaParser.blockStatement_return();
        retval.start = input.LT(1);

        int blockStatement_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.localVariableDeclarationStatement_return localVariableDeclarationStatement332 =null;

        JavaParser.classOrInterfaceDeclaration_return classOrInterfaceDeclaration333 =null;

        JavaParser.statement_return statement334 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 86) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:658:5: ( localVariableDeclarationStatement | classOrInterfaceDeclaration | statement )
            int alt105=3;
            switch ( input.LA(1) ) {
            case 96:
                {
                int LA105_1 = input.LA(2);

                if ( (synpred151_Java()) ) {
                    alt105=1;
                }
                else if ( (synpred152_Java()) ) {
                    alt105=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 105, 1, input);

                    throw nvae;

                }
                }
                break;
            case 78:
                {
                int LA105_2 = input.LA(2);

                if ( (synpred151_Java()) ) {
                    alt105=1;
                }
                else if ( (synpred152_Java()) ) {
                    alt105=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 105, 2, input);

                    throw nvae;

                }
                }
                break;
            case Identifier:
                {
                int LA105_3 = input.LA(2);

                if ( (synpred151_Java()) ) {
                    alt105=1;
                }
                else if ( (true) ) {
                    alt105=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 105, 3, input);

                    throw nvae;

                }
                }
                break;
            case 84:
            case 86:
            case 89:
            case 93:
            case 98:
            case 102:
            case 104:
            case 112:
                {
                int LA105_4 = input.LA(2);

                if ( (synpred151_Java()) ) {
                    alt105=1;
                }
                else if ( (true) ) {
                    alt105=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 105, 4, input);

                    throw nvae;

                }
                }
                break;
            case CLASS:
            case ENUM:
            case STATIC:
            case 83:
            case 103:
            case 108:
            case 109:
            case 110:
            case 113:
                {
                alt105=2;
                }
                break;
            case ASSERT:
            case CharacterLiteral:
            case DecimalLiteral:
            case FloatingPointLiteral:
            case HexLiteral:
            case OctalLiteral:
            case SEMI:
            case SUPER:
            case StringLiteral:
            case VOID:
            case 55:
            case 62:
            case 65:
            case 66:
            case 69:
            case 70:
            case 85:
            case 90:
            case 92:
            case 95:
            case 99:
            case 100:
            case 106:
            case 107:
            case 111:
            case 114:
            case 115:
            case 116:
            case 117:
            case 120:
            case 121:
            case 123:
            case 124:
            case 129:
                {
                alt105=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 105, 0, input);

                throw nvae;

            }

            switch (alt105) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:658:9: localVariableDeclarationStatement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_localVariableDeclarationStatement_in_blockStatement3862);
                    localVariableDeclarationStatement332=localVariableDeclarationStatement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, localVariableDeclarationStatement332.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:659:9: classOrInterfaceDeclaration
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_classOrInterfaceDeclaration_in_blockStatement3872);
                    classOrInterfaceDeclaration333=classOrInterfaceDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceDeclaration333.getTree());

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:660:9: statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_statement_in_blockStatement3882);
                    statement334=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement334.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 86, blockStatement_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "blockStatement"


    public static class localVariableDeclarationStatement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "localVariableDeclarationStatement"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:663:1: localVariableDeclarationStatement : localVariableDeclaration ';' ;
    public final JavaParser.localVariableDeclarationStatement_return localVariableDeclarationStatement() throws RecognitionException {
        JavaParser.localVariableDeclarationStatement_return retval = new JavaParser.localVariableDeclarationStatement_return();
        retval.start = input.LT(1);

        int localVariableDeclarationStatement_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal336=null;
        JavaParser.localVariableDeclaration_return localVariableDeclaration335 =null;


        CommonTree char_literal336_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 87) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:664:5: ( localVariableDeclaration ';' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:664:10: localVariableDeclaration ';'
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_localVariableDeclaration_in_localVariableDeclarationStatement3906);
            localVariableDeclaration335=localVariableDeclaration();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, localVariableDeclaration335.getTree());

            char_literal336=(Token)match(input,SEMI,FOLLOW_SEMI_in_localVariableDeclarationStatement3908); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal336_tree = 
            (CommonTree)adaptor.create(char_literal336)
            ;
            adaptor.addChild(root_0, char_literal336_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 87, localVariableDeclarationStatement_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "localVariableDeclarationStatement"


    public static class localVariableDeclaration_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "localVariableDeclaration"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:667:1: localVariableDeclaration : variableModifiers type variableDeclarators -> ^( MEMBER ( variableModifiers )? type variableDeclarators ) ;
    public final JavaParser.localVariableDeclaration_return localVariableDeclaration() throws RecognitionException {
        JavaParser.localVariableDeclaration_return retval = new JavaParser.localVariableDeclaration_return();
        retval.start = input.LT(1);

        int localVariableDeclaration_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.variableModifiers_return variableModifiers337 =null;

        JavaParser.type_return type338 =null;

        JavaParser.variableDeclarators_return variableDeclarators339 =null;


        RewriteRuleSubtreeStream stream_variableModifiers=new RewriteRuleSubtreeStream(adaptor,"rule variableModifiers");
        RewriteRuleSubtreeStream stream_variableDeclarators=new RewriteRuleSubtreeStream(adaptor,"rule variableDeclarators");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 88) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:668:5: ( variableModifiers type variableDeclarators -> ^( MEMBER ( variableModifiers )? type variableDeclarators ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:668:9: variableModifiers type variableDeclarators
            {
            pushFollow(FOLLOW_variableModifiers_in_localVariableDeclaration3927);
            variableModifiers337=variableModifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variableModifiers.add(variableModifiers337.getTree());

            pushFollow(FOLLOW_type_in_localVariableDeclaration3929);
            type338=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type.add(type338.getTree());

            pushFollow(FOLLOW_variableDeclarators_in_localVariableDeclaration3931);
            variableDeclarators339=variableDeclarators();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variableDeclarators.add(variableDeclarators339.getTree());

            // AST REWRITE
            // elements: type, variableDeclarators, variableModifiers
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 668:52: -> ^( MEMBER ( variableModifiers )? type variableDeclarators )
            {
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:668:55: ^( MEMBER ( variableModifiers )? type variableDeclarators )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(MEMBER, "MEMBER")
                , root_1);

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:668:64: ( variableModifiers )?
                if ( stream_variableModifiers.hasNext() ) {
                    adaptor.addChild(root_1, stream_variableModifiers.nextTree());

                }
                stream_variableModifiers.reset();

                adaptor.addChild(root_1, stream_type.nextTree());

                adaptor.addChild(root_1, stream_variableDeclarators.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 88, localVariableDeclaration_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "localVariableDeclaration"


    public static class variableModifiers_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "variableModifiers"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:671:1: variableModifiers : ( variableModifier )* ;
    public final JavaParser.variableModifiers_return variableModifiers() throws RecognitionException {
        JavaParser.variableModifiers_return retval = new JavaParser.variableModifiers_return();
        retval.start = input.LT(1);

        int variableModifiers_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.variableModifier_return variableModifier340 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 89) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:672:5: ( ( variableModifier )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:672:9: ( variableModifier )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:672:9: ( variableModifier )*
            loop106:
            do {
                int alt106=2;
                int LA106_0 = input.LA(1);

                if ( (LA106_0==78||LA106_0==96) ) {
                    alt106=1;
                }


                switch (alt106) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:672:9: variableModifier
            	    {
            	    pushFollow(FOLLOW_variableModifier_in_variableModifiers3967);
            	    variableModifier340=variableModifier();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, variableModifier340.getTree());

            	    }
            	    break;

            	default :
            	    break loop106;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 89, variableModifiers_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "variableModifiers"


    public static class statement_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statement"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:675:1: statement : ( block | ASSERT expression ( ':' expression )? ';' | 'if' parExpression statement ( options {k=1; } : 'else' statement )? | 'for' '(' forControl ')' statement | 'while' parExpression statement | 'do' statement 'while' parExpression ';' | 'try' block ( catches 'finally' block | catches | 'finally' block ) | 'switch' parExpression '{' switchBlockStatementGroups '}' | 'synchronized' parExpression block | 'return' ( expression )? ';' | 'throw' expression ';' | 'break' ( Identifier )? ';' | 'continue' ( Identifier )? ';' | ';' | statementExpression ';' | Identifier ':' statement );
    public final JavaParser.statement_return statement() throws RecognitionException {
        JavaParser.statement_return retval = new JavaParser.statement_return();
        retval.start = input.LT(1);

        int statement_StartIndex = input.index();

        CommonTree root_0 = null;

        Token ASSERT342=null;
        Token char_literal344=null;
        Token char_literal346=null;
        Token string_literal347=null;
        Token string_literal350=null;
        Token string_literal352=null;
        Token char_literal353=null;
        Token char_literal355=null;
        Token string_literal357=null;
        Token string_literal360=null;
        Token string_literal362=null;
        Token char_literal364=null;
        Token string_literal365=null;
        Token string_literal368=null;
        Token string_literal371=null;
        Token string_literal373=null;
        Token char_literal375=null;
        Token char_literal377=null;
        Token string_literal378=null;
        Token string_literal381=null;
        Token char_literal383=null;
        Token string_literal384=null;
        Token char_literal386=null;
        Token string_literal387=null;
        Token Identifier388=null;
        Token char_literal389=null;
        Token string_literal390=null;
        Token Identifier391=null;
        Token char_literal392=null;
        Token char_literal393=null;
        Token char_literal395=null;
        Token Identifier396=null;
        Token char_literal397=null;
        JavaParser.block_return block341 =null;

        JavaParser.expression_return expression343 =null;

        JavaParser.expression_return expression345 =null;

        JavaParser.parExpression_return parExpression348 =null;

        JavaParser.statement_return statement349 =null;

        JavaParser.statement_return statement351 =null;

        JavaParser.forControl_return forControl354 =null;

        JavaParser.statement_return statement356 =null;

        JavaParser.parExpression_return parExpression358 =null;

        JavaParser.statement_return statement359 =null;

        JavaParser.statement_return statement361 =null;

        JavaParser.parExpression_return parExpression363 =null;

        JavaParser.block_return block366 =null;

        JavaParser.catches_return catches367 =null;

        JavaParser.block_return block369 =null;

        JavaParser.catches_return catches370 =null;

        JavaParser.block_return block372 =null;

        JavaParser.parExpression_return parExpression374 =null;

        JavaParser.switchBlockStatementGroups_return switchBlockStatementGroups376 =null;

        JavaParser.parExpression_return parExpression379 =null;

        JavaParser.block_return block380 =null;

        JavaParser.expression_return expression382 =null;

        JavaParser.expression_return expression385 =null;

        JavaParser.statementExpression_return statementExpression394 =null;

        JavaParser.statement_return statement398 =null;


        CommonTree ASSERT342_tree=null;
        CommonTree char_literal344_tree=null;
        CommonTree char_literal346_tree=null;
        CommonTree string_literal347_tree=null;
        CommonTree string_literal350_tree=null;
        CommonTree string_literal352_tree=null;
        CommonTree char_literal353_tree=null;
        CommonTree char_literal355_tree=null;
        CommonTree string_literal357_tree=null;
        CommonTree string_literal360_tree=null;
        CommonTree string_literal362_tree=null;
        CommonTree char_literal364_tree=null;
        CommonTree string_literal365_tree=null;
        CommonTree string_literal368_tree=null;
        CommonTree string_literal371_tree=null;
        CommonTree string_literal373_tree=null;
        CommonTree char_literal375_tree=null;
        CommonTree char_literal377_tree=null;
        CommonTree string_literal378_tree=null;
        CommonTree string_literal381_tree=null;
        CommonTree char_literal383_tree=null;
        CommonTree string_literal384_tree=null;
        CommonTree char_literal386_tree=null;
        CommonTree string_literal387_tree=null;
        CommonTree Identifier388_tree=null;
        CommonTree char_literal389_tree=null;
        CommonTree string_literal390_tree=null;
        CommonTree Identifier391_tree=null;
        CommonTree char_literal392_tree=null;
        CommonTree char_literal393_tree=null;
        CommonTree char_literal395_tree=null;
        CommonTree Identifier396_tree=null;
        CommonTree char_literal397_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 90) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:676:5: ( block | ASSERT expression ( ':' expression )? ';' | 'if' parExpression statement ( options {k=1; } : 'else' statement )? | 'for' '(' forControl ')' statement | 'while' parExpression statement | 'do' statement 'while' parExpression ';' | 'try' block ( catches 'finally' block | catches | 'finally' block ) | 'switch' parExpression '{' switchBlockStatementGroups '}' | 'synchronized' parExpression block | 'return' ( expression )? ';' | 'throw' expression ';' | 'break' ( Identifier )? ';' | 'continue' ( Identifier )? ';' | ';' | statementExpression ';' | Identifier ':' statement )
            int alt113=16;
            switch ( input.LA(1) ) {
            case 124:
                {
                alt113=1;
                }
                break;
            case ASSERT:
                {
                alt113=2;
                }
                break;
            case 100:
                {
                alt113=3;
                }
                break;
            case 99:
                {
                alt113=4;
                }
                break;
            case 123:
                {
                alt113=5;
                }
                break;
            case 92:
                {
                alt113=6;
                }
                break;
            case 121:
                {
                alt113=7;
                }
                break;
            case 114:
                {
                alt113=8;
                }
                break;
            case 115:
                {
                alt113=9;
                }
                break;
            case 111:
                {
                alt113=10;
                }
                break;
            case 117:
                {
                alt113=11;
                }
                break;
            case 85:
                {
                alt113=12;
                }
                break;
            case 90:
                {
                alt113=13;
                }
                break;
            case SEMI:
                {
                alt113=14;
                }
                break;
            case CharacterLiteral:
            case DecimalLiteral:
            case FloatingPointLiteral:
            case HexLiteral:
            case OctalLiteral:
            case SUPER:
            case StringLiteral:
            case VOID:
            case 55:
            case 62:
            case 65:
            case 66:
            case 69:
            case 70:
            case 84:
            case 86:
            case 89:
            case 93:
            case 95:
            case 98:
            case 102:
            case 104:
            case 106:
            case 107:
            case 112:
            case 116:
            case 120:
            case 129:
                {
                alt113=15;
                }
                break;
            case Identifier:
                {
                int LA113_16 = input.LA(2);

                if ( (LA113_16==75) ) {
                    alt113=16;
                }
                else if ( (LA113_16==DOT||LA113_16==EQ||LA113_16==GT||LA113_16==LS||(LA113_16 >= SEMI && LA113_16 <= STAR)||(LA113_16 >= 56 && LA113_16 <= 62)||(LA113_16 >= 64 && LA113_16 <= 67)||(LA113_16 >= 69 && LA113_16 <= 71)||(LA113_16 >= 73 && LA113_16 <= 74)||(LA113_16 >= 76 && LA113_16 <= 77)||LA113_16==79||(LA113_16 >= 81 && LA113_16 <= 82)||LA113_16==101||(LA113_16 >= 125 && LA113_16 <= 127)) ) {
                    alt113=15;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 113, 16, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 113, 0, input);

                throw nvae;

            }

            switch (alt113) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:676:7: block
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_block_in_statement3985);
                    block341=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block341.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:677:9: ASSERT expression ( ':' expression )? ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    ASSERT342=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_statement3995); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ASSERT342_tree = 
                    (CommonTree)adaptor.create(ASSERT342)
                    ;
                    adaptor.addChild(root_0, ASSERT342_tree);
                    }

                    pushFollow(FOLLOW_expression_in_statement3997);
                    expression343=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression343.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:677:27: ( ':' expression )?
                    int alt107=2;
                    int LA107_0 = input.LA(1);

                    if ( (LA107_0==75) ) {
                        alt107=1;
                    }
                    switch (alt107) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:677:28: ':' expression
                            {
                            char_literal344=(Token)match(input,75,FOLLOW_75_in_statement4000); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            char_literal344_tree = 
                            (CommonTree)adaptor.create(char_literal344)
                            ;
                            adaptor.addChild(root_0, char_literal344_tree);
                            }

                            pushFollow(FOLLOW_expression_in_statement4002);
                            expression345=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression345.getTree());

                            }
                            break;

                    }


                    char_literal346=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4006); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal346_tree = 
                    (CommonTree)adaptor.create(char_literal346)
                    ;
                    adaptor.addChild(root_0, char_literal346_tree);
                    }

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:678:9: 'if' parExpression statement ( options {k=1; } : 'else' statement )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal347=(Token)match(input,100,FOLLOW_100_in_statement4016); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal347_tree = 
                    (CommonTree)adaptor.create(string_literal347)
                    ;
                    adaptor.addChild(root_0, string_literal347_tree);
                    }

                    pushFollow(FOLLOW_parExpression_in_statement4018);
                    parExpression348=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression348.getTree());

                    pushFollow(FOLLOW_statement_in_statement4020);
                    statement349=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement349.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:678:38: ( options {k=1; } : 'else' statement )?
                    int alt108=2;
                    int LA108_0 = input.LA(1);

                    if ( (LA108_0==94) ) {
                        int LA108_1 = input.LA(2);

                        if ( (synpred157_Java()) ) {
                            alt108=1;
                        }
                    }
                    switch (alt108) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:678:54: 'else' statement
                            {
                            string_literal350=(Token)match(input,94,FOLLOW_94_in_statement4030); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal350_tree = 
                            (CommonTree)adaptor.create(string_literal350)
                            ;
                            adaptor.addChild(root_0, string_literal350_tree);
                            }

                            pushFollow(FOLLOW_statement_in_statement4032);
                            statement351=statement();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, statement351.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:679:9: 'for' '(' forControl ')' statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal352=(Token)match(input,99,FOLLOW_99_in_statement4044); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal352_tree = 
                    (CommonTree)adaptor.create(string_literal352)
                    ;
                    adaptor.addChild(root_0, string_literal352_tree);
                    }

                    char_literal353=(Token)match(input,62,FOLLOW_62_in_statement4046); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal353_tree = 
                    (CommonTree)adaptor.create(char_literal353)
                    ;
                    adaptor.addChild(root_0, char_literal353_tree);
                    }

                    pushFollow(FOLLOW_forControl_in_statement4048);
                    forControl354=forControl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, forControl354.getTree());

                    char_literal355=(Token)match(input,63,FOLLOW_63_in_statement4050); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal355_tree = 
                    (CommonTree)adaptor.create(char_literal355)
                    ;
                    adaptor.addChild(root_0, char_literal355_tree);
                    }

                    pushFollow(FOLLOW_statement_in_statement4052);
                    statement356=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement356.getTree());

                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:680:9: 'while' parExpression statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal357=(Token)match(input,123,FOLLOW_123_in_statement4062); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal357_tree = 
                    (CommonTree)adaptor.create(string_literal357)
                    ;
                    adaptor.addChild(root_0, string_literal357_tree);
                    }

                    pushFollow(FOLLOW_parExpression_in_statement4064);
                    parExpression358=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression358.getTree());

                    pushFollow(FOLLOW_statement_in_statement4066);
                    statement359=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement359.getTree());

                    }
                    break;
                case 6 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:681:9: 'do' statement 'while' parExpression ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal360=(Token)match(input,92,FOLLOW_92_in_statement4076); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal360_tree = 
                    (CommonTree)adaptor.create(string_literal360)
                    ;
                    adaptor.addChild(root_0, string_literal360_tree);
                    }

                    pushFollow(FOLLOW_statement_in_statement4078);
                    statement361=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement361.getTree());

                    string_literal362=(Token)match(input,123,FOLLOW_123_in_statement4080); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal362_tree = 
                    (CommonTree)adaptor.create(string_literal362)
                    ;
                    adaptor.addChild(root_0, string_literal362_tree);
                    }

                    pushFollow(FOLLOW_parExpression_in_statement4082);
                    parExpression363=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression363.getTree());

                    char_literal364=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4084); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal364_tree = 
                    (CommonTree)adaptor.create(char_literal364)
                    ;
                    adaptor.addChild(root_0, char_literal364_tree);
                    }

                    }
                    break;
                case 7 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:682:9: 'try' block ( catches 'finally' block | catches | 'finally' block )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal365=(Token)match(input,121,FOLLOW_121_in_statement4094); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal365_tree = 
                    (CommonTree)adaptor.create(string_literal365)
                    ;
                    adaptor.addChild(root_0, string_literal365_tree);
                    }

                    pushFollow(FOLLOW_block_in_statement4096);
                    block366=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block366.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:683:9: ( catches 'finally' block | catches | 'finally' block )
                    int alt109=3;
                    int LA109_0 = input.LA(1);

                    if ( (LA109_0==88) ) {
                        int LA109_1 = input.LA(2);

                        if ( (synpred162_Java()) ) {
                            alt109=1;
                        }
                        else if ( (synpred163_Java()) ) {
                            alt109=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 109, 1, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA109_0==97) ) {
                        alt109=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 109, 0, input);

                        throw nvae;

                    }
                    switch (alt109) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:683:11: catches 'finally' block
                            {
                            pushFollow(FOLLOW_catches_in_statement4108);
                            catches367=catches();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, catches367.getTree());

                            string_literal368=(Token)match(input,97,FOLLOW_97_in_statement4110); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal368_tree = 
                            (CommonTree)adaptor.create(string_literal368)
                            ;
                            adaptor.addChild(root_0, string_literal368_tree);
                            }

                            pushFollow(FOLLOW_block_in_statement4112);
                            block369=block();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, block369.getTree());

                            }
                            break;
                        case 2 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:684:11: catches
                            {
                            pushFollow(FOLLOW_catches_in_statement4124);
                            catches370=catches();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, catches370.getTree());

                            }
                            break;
                        case 3 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:685:13: 'finally' block
                            {
                            string_literal371=(Token)match(input,97,FOLLOW_97_in_statement4138); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            string_literal371_tree = 
                            (CommonTree)adaptor.create(string_literal371)
                            ;
                            adaptor.addChild(root_0, string_literal371_tree);
                            }

                            pushFollow(FOLLOW_block_in_statement4140);
                            block372=block();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, block372.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 8 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:687:9: 'switch' parExpression '{' switchBlockStatementGroups '}'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal373=(Token)match(input,114,FOLLOW_114_in_statement4160); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal373_tree = 
                    (CommonTree)adaptor.create(string_literal373)
                    ;
                    adaptor.addChild(root_0, string_literal373_tree);
                    }

                    pushFollow(FOLLOW_parExpression_in_statement4162);
                    parExpression374=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression374.getTree());

                    char_literal375=(Token)match(input,124,FOLLOW_124_in_statement4164); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal375_tree = 
                    (CommonTree)adaptor.create(char_literal375)
                    ;
                    adaptor.addChild(root_0, char_literal375_tree);
                    }

                    pushFollow(FOLLOW_switchBlockStatementGroups_in_statement4166);
                    switchBlockStatementGroups376=switchBlockStatementGroups();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, switchBlockStatementGroups376.getTree());

                    char_literal377=(Token)match(input,128,FOLLOW_128_in_statement4168); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal377_tree = 
                    (CommonTree)adaptor.create(char_literal377)
                    ;
                    adaptor.addChild(root_0, char_literal377_tree);
                    }

                    }
                    break;
                case 9 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:688:9: 'synchronized' parExpression block
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal378=(Token)match(input,115,FOLLOW_115_in_statement4178); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal378_tree = 
                    (CommonTree)adaptor.create(string_literal378)
                    ;
                    adaptor.addChild(root_0, string_literal378_tree);
                    }

                    pushFollow(FOLLOW_parExpression_in_statement4180);
                    parExpression379=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression379.getTree());

                    pushFollow(FOLLOW_block_in_statement4182);
                    block380=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, block380.getTree());

                    }
                    break;
                case 10 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:689:9: 'return' ( expression )? ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal381=(Token)match(input,111,FOLLOW_111_in_statement4192); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal381_tree = 
                    (CommonTree)adaptor.create(string_literal381)
                    ;
                    adaptor.addChild(root_0, string_literal381_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:689:18: ( expression )?
                    int alt110=2;
                    int LA110_0 = input.LA(1);

                    if ( (LA110_0==CharacterLiteral||LA110_0==DecimalLiteral||LA110_0==FloatingPointLiteral||LA110_0==HexLiteral||LA110_0==Identifier||LA110_0==OctalLiteral||(LA110_0 >= SUPER && LA110_0 <= StringLiteral)||LA110_0==VOID||LA110_0==55||LA110_0==62||(LA110_0 >= 65 && LA110_0 <= 66)||(LA110_0 >= 69 && LA110_0 <= 70)||LA110_0==84||LA110_0==86||LA110_0==89||LA110_0==93||LA110_0==95||LA110_0==98||LA110_0==102||LA110_0==104||(LA110_0 >= 106 && LA110_0 <= 107)||LA110_0==112||LA110_0==116||LA110_0==120||LA110_0==129) ) {
                        alt110=1;
                    }
                    switch (alt110) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:689:18: expression
                            {
                            pushFollow(FOLLOW_expression_in_statement4194);
                            expression382=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression382.getTree());

                            }
                            break;

                    }


                    char_literal383=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4197); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal383_tree = 
                    (CommonTree)adaptor.create(char_literal383)
                    ;
                    adaptor.addChild(root_0, char_literal383_tree);
                    }

                    }
                    break;
                case 11 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:690:9: 'throw' expression ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal384=(Token)match(input,117,FOLLOW_117_in_statement4207); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal384_tree = 
                    (CommonTree)adaptor.create(string_literal384)
                    ;
                    adaptor.addChild(root_0, string_literal384_tree);
                    }

                    pushFollow(FOLLOW_expression_in_statement4209);
                    expression385=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression385.getTree());

                    char_literal386=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4211); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal386_tree = 
                    (CommonTree)adaptor.create(char_literal386)
                    ;
                    adaptor.addChild(root_0, char_literal386_tree);
                    }

                    }
                    break;
                case 12 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:691:9: 'break' ( Identifier )? ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal387=(Token)match(input,85,FOLLOW_85_in_statement4221); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal387_tree = 
                    (CommonTree)adaptor.create(string_literal387)
                    ;
                    adaptor.addChild(root_0, string_literal387_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:691:17: ( Identifier )?
                    int alt111=2;
                    int LA111_0 = input.LA(1);

                    if ( (LA111_0==Identifier) ) {
                        alt111=1;
                    }
                    switch (alt111) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:691:17: Identifier
                            {
                            Identifier388=(Token)match(input,Identifier,FOLLOW_Identifier_in_statement4223); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            Identifier388_tree = 
                            (CommonTree)adaptor.create(Identifier388)
                            ;
                            adaptor.addChild(root_0, Identifier388_tree);
                            }

                            }
                            break;

                    }


                    char_literal389=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4226); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal389_tree = 
                    (CommonTree)adaptor.create(char_literal389)
                    ;
                    adaptor.addChild(root_0, char_literal389_tree);
                    }

                    }
                    break;
                case 13 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:692:9: 'continue' ( Identifier )? ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal390=(Token)match(input,90,FOLLOW_90_in_statement4236); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal390_tree = 
                    (CommonTree)adaptor.create(string_literal390)
                    ;
                    adaptor.addChild(root_0, string_literal390_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:692:20: ( Identifier )?
                    int alt112=2;
                    int LA112_0 = input.LA(1);

                    if ( (LA112_0==Identifier) ) {
                        alt112=1;
                    }
                    switch (alt112) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:692:20: Identifier
                            {
                            Identifier391=(Token)match(input,Identifier,FOLLOW_Identifier_in_statement4238); if (state.failed) return retval;
                            if ( state.backtracking==0 ) {
                            Identifier391_tree = 
                            (CommonTree)adaptor.create(Identifier391)
                            ;
                            adaptor.addChild(root_0, Identifier391_tree);
                            }

                            }
                            break;

                    }


                    char_literal392=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4241); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal392_tree = 
                    (CommonTree)adaptor.create(char_literal392)
                    ;
                    adaptor.addChild(root_0, char_literal392_tree);
                    }

                    }
                    break;
                case 14 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:693:9: ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal393=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4251); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal393_tree = 
                    (CommonTree)adaptor.create(char_literal393)
                    ;
                    adaptor.addChild(root_0, char_literal393_tree);
                    }

                    }
                    break;
                case 15 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:694:9: statementExpression ';'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_statementExpression_in_statement4262);
                    statementExpression394=statementExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statementExpression394.getTree());

                    char_literal395=(Token)match(input,SEMI,FOLLOW_SEMI_in_statement4264); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal395_tree = 
                    (CommonTree)adaptor.create(char_literal395)
                    ;
                    adaptor.addChild(root_0, char_literal395_tree);
                    }

                    }
                    break;
                case 16 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:695:9: Identifier ':' statement
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    Identifier396=(Token)match(input,Identifier,FOLLOW_Identifier_in_statement4274); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Identifier396_tree = 
                    (CommonTree)adaptor.create(Identifier396)
                    ;
                    adaptor.addChild(root_0, Identifier396_tree);
                    }

                    char_literal397=(Token)match(input,75,FOLLOW_75_in_statement4276); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal397_tree = 
                    (CommonTree)adaptor.create(char_literal397)
                    ;
                    adaptor.addChild(root_0, char_literal397_tree);
                    }

                    pushFollow(FOLLOW_statement_in_statement4278);
                    statement398=statement();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement398.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 90, statement_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "statement"


    public static class catches_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "catches"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:698:1: catches : catchClause ( catchClause )* ;
    public final JavaParser.catches_return catches() throws RecognitionException {
        JavaParser.catches_return retval = new JavaParser.catches_return();
        retval.start = input.LT(1);

        int catches_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.catchClause_return catchClause399 =null;

        JavaParser.catchClause_return catchClause400 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 91) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:699:5: ( catchClause ( catchClause )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:699:9: catchClause ( catchClause )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_catchClause_in_catches4301);
            catchClause399=catchClause();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, catchClause399.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:699:21: ( catchClause )*
            loop114:
            do {
                int alt114=2;
                int LA114_0 = input.LA(1);

                if ( (LA114_0==88) ) {
                    alt114=1;
                }


                switch (alt114) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:699:22: catchClause
            	    {
            	    pushFollow(FOLLOW_catchClause_in_catches4304);
            	    catchClause400=catchClause();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, catchClause400.getTree());

            	    }
            	    break;

            	default :
            	    break loop114;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 91, catches_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "catches"


    public static class catchClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "catchClause"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:702:1: catchClause : 'catch' '(' formalParameter ')' block ;
    public final JavaParser.catchClause_return catchClause() throws RecognitionException {
        JavaParser.catchClause_return retval = new JavaParser.catchClause_return();
        retval.start = input.LT(1);

        int catchClause_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal401=null;
        Token char_literal402=null;
        Token char_literal404=null;
        JavaParser.formalParameter_return formalParameter403 =null;

        JavaParser.block_return block405 =null;


        CommonTree string_literal401_tree=null;
        CommonTree char_literal402_tree=null;
        CommonTree char_literal404_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 92) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:703:5: ( 'catch' '(' formalParameter ')' block )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:703:9: 'catch' '(' formalParameter ')' block
            {
            root_0 = (CommonTree)adaptor.nil();


            string_literal401=(Token)match(input,88,FOLLOW_88_in_catchClause4329); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            string_literal401_tree = 
            (CommonTree)adaptor.create(string_literal401)
            ;
            adaptor.addChild(root_0, string_literal401_tree);
            }

            char_literal402=(Token)match(input,62,FOLLOW_62_in_catchClause4331); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal402_tree = 
            (CommonTree)adaptor.create(char_literal402)
            ;
            adaptor.addChild(root_0, char_literal402_tree);
            }

            pushFollow(FOLLOW_formalParameter_in_catchClause4333);
            formalParameter403=formalParameter();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, formalParameter403.getTree());

            char_literal404=(Token)match(input,63,FOLLOW_63_in_catchClause4335); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal404_tree = 
            (CommonTree)adaptor.create(char_literal404)
            ;
            adaptor.addChild(root_0, char_literal404_tree);
            }

            pushFollow(FOLLOW_block_in_catchClause4337);
            block405=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, block405.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 92, catchClause_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "catchClause"


    public static class formalParameter_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "formalParameter"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:706:1: formalParameter : variableModifiers type variableDeclaratorId -> ^( MEMBER ( variableModifiers )? type variableDeclaratorId ) ;
    public final JavaParser.formalParameter_return formalParameter() throws RecognitionException {
        JavaParser.formalParameter_return retval = new JavaParser.formalParameter_return();
        retval.start = input.LT(1);

        int formalParameter_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.variableModifiers_return variableModifiers406 =null;

        JavaParser.type_return type407 =null;

        JavaParser.variableDeclaratorId_return variableDeclaratorId408 =null;


        RewriteRuleSubtreeStream stream_variableDeclaratorId=new RewriteRuleSubtreeStream(adaptor,"rule variableDeclaratorId");
        RewriteRuleSubtreeStream stream_variableModifiers=new RewriteRuleSubtreeStream(adaptor,"rule variableModifiers");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 93) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:707:5: ( variableModifiers type variableDeclaratorId -> ^( MEMBER ( variableModifiers )? type variableDeclaratorId ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:707:9: variableModifiers type variableDeclaratorId
            {
            pushFollow(FOLLOW_variableModifiers_in_formalParameter4356);
            variableModifiers406=variableModifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variableModifiers.add(variableModifiers406.getTree());

            pushFollow(FOLLOW_type_in_formalParameter4358);
            type407=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_type.add(type407.getTree());

            pushFollow(FOLLOW_variableDeclaratorId_in_formalParameter4360);
            variableDeclaratorId408=variableDeclaratorId();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_variableDeclaratorId.add(variableDeclaratorId408.getTree());

            // AST REWRITE
            // elements: variableDeclaratorId, variableModifiers, type
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 707:53: -> ^( MEMBER ( variableModifiers )? type variableDeclaratorId )
            {
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:707:56: ^( MEMBER ( variableModifiers )? type variableDeclaratorId )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(MEMBER, "MEMBER")
                , root_1);

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:707:65: ( variableModifiers )?
                if ( stream_variableModifiers.hasNext() ) {
                    adaptor.addChild(root_1, stream_variableModifiers.nextTree());

                }
                stream_variableModifiers.reset();

                adaptor.addChild(root_1, stream_type.nextTree());

                adaptor.addChild(root_1, stream_variableDeclaratorId.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 93, formalParameter_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "formalParameter"


    public static class switchBlockStatementGroups_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "switchBlockStatementGroups"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:710:1: switchBlockStatementGroups : ( switchBlockStatementGroup )* ;
    public final JavaParser.switchBlockStatementGroups_return switchBlockStatementGroups() throws RecognitionException {
        JavaParser.switchBlockStatementGroups_return retval = new JavaParser.switchBlockStatementGroups_return();
        retval.start = input.LT(1);

        int switchBlockStatementGroups_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.switchBlockStatementGroup_return switchBlockStatementGroup409 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 94) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:711:5: ( ( switchBlockStatementGroup )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:711:9: ( switchBlockStatementGroup )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:711:9: ( switchBlockStatementGroup )*
            loop115:
            do {
                int alt115=2;
                int LA115_0 = input.LA(1);

                if ( (LA115_0==87||LA115_0==91) ) {
                    alt115=1;
                }


                switch (alt115) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:711:10: switchBlockStatementGroup
            	    {
            	    pushFollow(FOLLOW_switchBlockStatementGroup_in_switchBlockStatementGroups4401);
            	    switchBlockStatementGroup409=switchBlockStatementGroup();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, switchBlockStatementGroup409.getTree());

            	    }
            	    break;

            	default :
            	    break loop115;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 94, switchBlockStatementGroups_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "switchBlockStatementGroups"


    public static class switchBlockStatementGroup_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "switchBlockStatementGroup"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:718:1: switchBlockStatementGroup : ( switchLabel )+ ( blockStatement )* ;
    public final JavaParser.switchBlockStatementGroup_return switchBlockStatementGroup() throws RecognitionException {
        JavaParser.switchBlockStatementGroup_return retval = new JavaParser.switchBlockStatementGroup_return();
        retval.start = input.LT(1);

        int switchBlockStatementGroup_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.switchLabel_return switchLabel410 =null;

        JavaParser.blockStatement_return blockStatement411 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 95) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:719:5: ( ( switchLabel )+ ( blockStatement )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:719:9: ( switchLabel )+ ( blockStatement )*
            {
            root_0 = (CommonTree)adaptor.nil();


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:719:9: ( switchLabel )+
            int cnt116=0;
            loop116:
            do {
                int alt116=2;
                int LA116_0 = input.LA(1);

                if ( (LA116_0==87) ) {
                    int LA116_2 = input.LA(2);

                    if ( (synpred178_Java()) ) {
                        alt116=1;
                    }


                }
                else if ( (LA116_0==91) ) {
                    int LA116_3 = input.LA(2);

                    if ( (synpred178_Java()) ) {
                        alt116=1;
                    }


                }


                switch (alt116) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:719:9: switchLabel
            	    {
            	    pushFollow(FOLLOW_switchLabel_in_switchBlockStatementGroup4428);
            	    switchLabel410=switchLabel();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, switchLabel410.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt116 >= 1 ) break loop116;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(116, input);
                        throw eee;
                }
                cnt116++;
            } while (true);


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:719:22: ( blockStatement )*
            loop117:
            do {
                int alt117=2;
                int LA117_0 = input.LA(1);

                if ( (LA117_0==ASSERT||LA117_0==CLASS||LA117_0==CharacterLiteral||(LA117_0 >= DecimalLiteral && LA117_0 <= ENUM)||LA117_0==FloatingPointLiteral||LA117_0==HexLiteral||LA117_0==Identifier||LA117_0==OctalLiteral||LA117_0==SEMI||(LA117_0 >= STATIC && LA117_0 <= StringLiteral)||LA117_0==VOID||LA117_0==55||LA117_0==62||(LA117_0 >= 65 && LA117_0 <= 66)||(LA117_0 >= 69 && LA117_0 <= 70)||LA117_0==78||(LA117_0 >= 83 && LA117_0 <= 86)||(LA117_0 >= 89 && LA117_0 <= 90)||(LA117_0 >= 92 && LA117_0 <= 93)||(LA117_0 >= 95 && LA117_0 <= 96)||(LA117_0 >= 98 && LA117_0 <= 100)||(LA117_0 >= 102 && LA117_0 <= 104)||(LA117_0 >= 106 && LA117_0 <= 117)||(LA117_0 >= 120 && LA117_0 <= 121)||(LA117_0 >= 123 && LA117_0 <= 124)||LA117_0==129) ) {
                    alt117=1;
                }


                switch (alt117) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:719:22: blockStatement
            	    {
            	    pushFollow(FOLLOW_blockStatement_in_switchBlockStatementGroup4431);
            	    blockStatement411=blockStatement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, blockStatement411.getTree());

            	    }
            	    break;

            	default :
            	    break loop117;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 95, switchBlockStatementGroup_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "switchBlockStatementGroup"


    public static class switchLabel_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "switchLabel"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:722:1: switchLabel : ( 'case' constantExpression ':' | 'case' enumConstantName ':' | 'default' ':' );
    public final JavaParser.switchLabel_return switchLabel() throws RecognitionException {
        JavaParser.switchLabel_return retval = new JavaParser.switchLabel_return();
        retval.start = input.LT(1);

        int switchLabel_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal412=null;
        Token char_literal414=null;
        Token string_literal415=null;
        Token char_literal417=null;
        Token string_literal418=null;
        Token char_literal419=null;
        JavaParser.constantExpression_return constantExpression413 =null;

        JavaParser.enumConstantName_return enumConstantName416 =null;


        CommonTree string_literal412_tree=null;
        CommonTree char_literal414_tree=null;
        CommonTree string_literal415_tree=null;
        CommonTree char_literal417_tree=null;
        CommonTree string_literal418_tree=null;
        CommonTree char_literal419_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 96) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:723:5: ( 'case' constantExpression ':' | 'case' enumConstantName ':' | 'default' ':' )
            int alt118=3;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==87) ) {
                int LA118_1 = input.LA(2);

                if ( (LA118_1==CharacterLiteral||LA118_1==DecimalLiteral||LA118_1==FloatingPointLiteral||LA118_1==HexLiteral||LA118_1==OctalLiteral||(LA118_1 >= SUPER && LA118_1 <= StringLiteral)||LA118_1==VOID||LA118_1==55||LA118_1==62||(LA118_1 >= 65 && LA118_1 <= 66)||(LA118_1 >= 69 && LA118_1 <= 70)||LA118_1==84||LA118_1==86||LA118_1==89||LA118_1==93||LA118_1==95||LA118_1==98||LA118_1==102||LA118_1==104||(LA118_1 >= 106 && LA118_1 <= 107)||LA118_1==112||LA118_1==116||LA118_1==120||LA118_1==129) ) {
                    alt118=1;
                }
                else if ( (LA118_1==Identifier) ) {
                    int LA118_4 = input.LA(3);

                    if ( (LA118_4==DOT||LA118_4==EQ||LA118_4==GT||LA118_4==LS||LA118_4==STAR||(LA118_4 >= 56 && LA118_4 <= 62)||(LA118_4 >= 64 && LA118_4 <= 67)||(LA118_4 >= 69 && LA118_4 <= 71)||(LA118_4 >= 73 && LA118_4 <= 74)||(LA118_4 >= 76 && LA118_4 <= 77)||LA118_4==79||(LA118_4 >= 81 && LA118_4 <= 82)||LA118_4==101||(LA118_4 >= 125 && LA118_4 <= 127)) ) {
                        alt118=1;
                    }
                    else if ( (LA118_4==75) ) {
                        int LA118_5 = input.LA(4);

                        if ( (synpred180_Java()) ) {
                            alt118=1;
                        }
                        else if ( (synpred181_Java()) ) {
                            alt118=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 118, 5, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 118, 4, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 118, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA118_0==91) ) {
                alt118=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 118, 0, input);

                throw nvae;

            }
            switch (alt118) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:723:9: 'case' constantExpression ':'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal412=(Token)match(input,87,FOLLOW_87_in_switchLabel4455); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal412_tree = 
                    (CommonTree)adaptor.create(string_literal412)
                    ;
                    adaptor.addChild(root_0, string_literal412_tree);
                    }

                    pushFollow(FOLLOW_constantExpression_in_switchLabel4457);
                    constantExpression413=constantExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, constantExpression413.getTree());

                    char_literal414=(Token)match(input,75,FOLLOW_75_in_switchLabel4459); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal414_tree = 
                    (CommonTree)adaptor.create(char_literal414)
                    ;
                    adaptor.addChild(root_0, char_literal414_tree);
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:724:9: 'case' enumConstantName ':'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal415=(Token)match(input,87,FOLLOW_87_in_switchLabel4469); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal415_tree = 
                    (CommonTree)adaptor.create(string_literal415)
                    ;
                    adaptor.addChild(root_0, string_literal415_tree);
                    }

                    pushFollow(FOLLOW_enumConstantName_in_switchLabel4471);
                    enumConstantName416=enumConstantName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enumConstantName416.getTree());

                    char_literal417=(Token)match(input,75,FOLLOW_75_in_switchLabel4473); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal417_tree = 
                    (CommonTree)adaptor.create(char_literal417)
                    ;
                    adaptor.addChild(root_0, char_literal417_tree);
                    }

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:725:9: 'default' ':'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal418=(Token)match(input,91,FOLLOW_91_in_switchLabel4483); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal418_tree = 
                    (CommonTree)adaptor.create(string_literal418)
                    ;
                    adaptor.addChild(root_0, string_literal418_tree);
                    }

                    char_literal419=(Token)match(input,75,FOLLOW_75_in_switchLabel4485); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal419_tree = 
                    (CommonTree)adaptor.create(char_literal419)
                    ;
                    adaptor.addChild(root_0, char_literal419_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 96, switchLabel_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "switchLabel"


    public static class forControl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "forControl"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:728:1: forControl options {k=3; } : ( enhancedForControl | ( forInit )? ';' ( expression )? ';' ( forUpdate )? );
    public final JavaParser.forControl_return forControl() throws RecognitionException {
        JavaParser.forControl_return retval = new JavaParser.forControl_return();
        retval.start = input.LT(1);

        int forControl_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal422=null;
        Token char_literal424=null;
        JavaParser.enhancedForControl_return enhancedForControl420 =null;

        JavaParser.forInit_return forInit421 =null;

        JavaParser.expression_return expression423 =null;

        JavaParser.forUpdate_return forUpdate425 =null;


        CommonTree char_literal422_tree=null;
        CommonTree char_literal424_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 97) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:730:5: ( enhancedForControl | ( forInit )? ';' ( expression )? ';' ( forUpdate )? )
            int alt122=2;
            alt122 = dfa122.predict(input);
            switch (alt122) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:730:9: enhancedForControl
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_enhancedForControl_in_forControl4516);
                    enhancedForControl420=enhancedForControl();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, enhancedForControl420.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:731:9: ( forInit )? ';' ( expression )? ';' ( forUpdate )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:731:9: ( forInit )?
                    int alt119=2;
                    int LA119_0 = input.LA(1);

                    if ( (LA119_0==CharacterLiteral||LA119_0==DecimalLiteral||LA119_0==FloatingPointLiteral||LA119_0==HexLiteral||LA119_0==Identifier||LA119_0==OctalLiteral||(LA119_0 >= SUPER && LA119_0 <= StringLiteral)||LA119_0==VOID||LA119_0==55||LA119_0==62||(LA119_0 >= 65 && LA119_0 <= 66)||(LA119_0 >= 69 && LA119_0 <= 70)||LA119_0==78||LA119_0==84||LA119_0==86||LA119_0==89||LA119_0==93||(LA119_0 >= 95 && LA119_0 <= 96)||LA119_0==98||LA119_0==102||LA119_0==104||(LA119_0 >= 106 && LA119_0 <= 107)||LA119_0==112||LA119_0==116||LA119_0==120||LA119_0==129) ) {
                        alt119=1;
                    }
                    switch (alt119) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:731:9: forInit
                            {
                            pushFollow(FOLLOW_forInit_in_forControl4526);
                            forInit421=forInit();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, forInit421.getTree());

                            }
                            break;

                    }


                    char_literal422=(Token)match(input,SEMI,FOLLOW_SEMI_in_forControl4529); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal422_tree = 
                    (CommonTree)adaptor.create(char_literal422)
                    ;
                    adaptor.addChild(root_0, char_literal422_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:731:22: ( expression )?
                    int alt120=2;
                    int LA120_0 = input.LA(1);

                    if ( (LA120_0==CharacterLiteral||LA120_0==DecimalLiteral||LA120_0==FloatingPointLiteral||LA120_0==HexLiteral||LA120_0==Identifier||LA120_0==OctalLiteral||(LA120_0 >= SUPER && LA120_0 <= StringLiteral)||LA120_0==VOID||LA120_0==55||LA120_0==62||(LA120_0 >= 65 && LA120_0 <= 66)||(LA120_0 >= 69 && LA120_0 <= 70)||LA120_0==84||LA120_0==86||LA120_0==89||LA120_0==93||LA120_0==95||LA120_0==98||LA120_0==102||LA120_0==104||(LA120_0 >= 106 && LA120_0 <= 107)||LA120_0==112||LA120_0==116||LA120_0==120||LA120_0==129) ) {
                        alt120=1;
                    }
                    switch (alt120) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:731:22: expression
                            {
                            pushFollow(FOLLOW_expression_in_forControl4531);
                            expression423=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression423.getTree());

                            }
                            break;

                    }


                    char_literal424=(Token)match(input,SEMI,FOLLOW_SEMI_in_forControl4534); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal424_tree = 
                    (CommonTree)adaptor.create(char_literal424)
                    ;
                    adaptor.addChild(root_0, char_literal424_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:731:38: ( forUpdate )?
                    int alt121=2;
                    int LA121_0 = input.LA(1);

                    if ( (LA121_0==CharacterLiteral||LA121_0==DecimalLiteral||LA121_0==FloatingPointLiteral||LA121_0==HexLiteral||LA121_0==Identifier||LA121_0==OctalLiteral||(LA121_0 >= SUPER && LA121_0 <= StringLiteral)||LA121_0==VOID||LA121_0==55||LA121_0==62||(LA121_0 >= 65 && LA121_0 <= 66)||(LA121_0 >= 69 && LA121_0 <= 70)||LA121_0==84||LA121_0==86||LA121_0==89||LA121_0==93||LA121_0==95||LA121_0==98||LA121_0==102||LA121_0==104||(LA121_0 >= 106 && LA121_0 <= 107)||LA121_0==112||LA121_0==116||LA121_0==120||LA121_0==129) ) {
                        alt121=1;
                    }
                    switch (alt121) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:731:38: forUpdate
                            {
                            pushFollow(FOLLOW_forUpdate_in_forControl4536);
                            forUpdate425=forUpdate();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, forUpdate425.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 97, forControl_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "forControl"


    public static class forInit_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "forInit"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:734:1: forInit : ( localVariableDeclaration | expressionList );
    public final JavaParser.forInit_return forInit() throws RecognitionException {
        JavaParser.forInit_return retval = new JavaParser.forInit_return();
        retval.start = input.LT(1);

        int forInit_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.localVariableDeclaration_return localVariableDeclaration426 =null;

        JavaParser.expressionList_return expressionList427 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 98) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:735:5: ( localVariableDeclaration | expressionList )
            int alt123=2;
            switch ( input.LA(1) ) {
            case 78:
            case 96:
                {
                alt123=1;
                }
                break;
            case Identifier:
                {
                int LA123_3 = input.LA(2);

                if ( (synpred186_Java()) ) {
                    alt123=1;
                }
                else if ( (true) ) {
                    alt123=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 123, 3, input);

                    throw nvae;

                }
                }
                break;
            case 84:
            case 86:
            case 89:
            case 93:
            case 98:
            case 102:
            case 104:
            case 112:
                {
                int LA123_4 = input.LA(2);

                if ( (synpred186_Java()) ) {
                    alt123=1;
                }
                else if ( (true) ) {
                    alt123=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 123, 4, input);

                    throw nvae;

                }
                }
                break;
            case CharacterLiteral:
            case DecimalLiteral:
            case FloatingPointLiteral:
            case HexLiteral:
            case OctalLiteral:
            case SUPER:
            case StringLiteral:
            case VOID:
            case 55:
            case 62:
            case 65:
            case 66:
            case 69:
            case 70:
            case 95:
            case 106:
            case 107:
            case 116:
            case 120:
            case 129:
                {
                alt123=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 123, 0, input);

                throw nvae;

            }

            switch (alt123) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:735:9: localVariableDeclaration
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_localVariableDeclaration_in_forInit4556);
                    localVariableDeclaration426=localVariableDeclaration();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, localVariableDeclaration426.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:736:9: expressionList
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_expressionList_in_forInit4566);
                    expressionList427=expressionList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionList427.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 98, forInit_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "forInit"


    public static class enhancedForControl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "enhancedForControl"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:739:1: enhancedForControl : variableModifiers type Identifier ':' expression ;
    public final JavaParser.enhancedForControl_return enhancedForControl() throws RecognitionException {
        JavaParser.enhancedForControl_return retval = new JavaParser.enhancedForControl_return();
        retval.start = input.LT(1);

        int enhancedForControl_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier430=null;
        Token char_literal431=null;
        JavaParser.variableModifiers_return variableModifiers428 =null;

        JavaParser.type_return type429 =null;

        JavaParser.expression_return expression432 =null;


        CommonTree Identifier430_tree=null;
        CommonTree char_literal431_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 99) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:740:5: ( variableModifiers type Identifier ':' expression )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:740:9: variableModifiers type Identifier ':' expression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_variableModifiers_in_enhancedForControl4589);
            variableModifiers428=variableModifiers();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, variableModifiers428.getTree());

            pushFollow(FOLLOW_type_in_enhancedForControl4591);
            type429=type();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, type429.getTree());

            Identifier430=(Token)match(input,Identifier,FOLLOW_Identifier_in_enhancedForControl4593); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier430_tree = 
            (CommonTree)adaptor.create(Identifier430)
            ;
            adaptor.addChild(root_0, Identifier430_tree);
            }

            char_literal431=(Token)match(input,75,FOLLOW_75_in_enhancedForControl4595); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal431_tree = 
            (CommonTree)adaptor.create(char_literal431)
            ;
            adaptor.addChild(root_0, char_literal431_tree);
            }

            pushFollow(FOLLOW_expression_in_enhancedForControl4597);
            expression432=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression432.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 99, enhancedForControl_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "enhancedForControl"


    public static class forUpdate_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "forUpdate"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:743:1: forUpdate : expressionList ;
    public final JavaParser.forUpdate_return forUpdate() throws RecognitionException {
        JavaParser.forUpdate_return retval = new JavaParser.forUpdate_return();
        retval.start = input.LT(1);

        int forUpdate_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.expressionList_return expressionList433 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 100) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:744:5: ( expressionList )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:744:9: expressionList
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_expressionList_in_forUpdate4616);
            expressionList433=expressionList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expressionList433.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 100, forUpdate_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "forUpdate"


    public static class parExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:749:1: parExpression : '(' expression ')' ;
    public final JavaParser.parExpression_return parExpression() throws RecognitionException {
        JavaParser.parExpression_return retval = new JavaParser.parExpression_return();
        retval.start = input.LT(1);

        int parExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal434=null;
        Token char_literal436=null;
        JavaParser.expression_return expression435 =null;


        CommonTree char_literal434_tree=null;
        CommonTree char_literal436_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 101) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:750:5: ( '(' expression ')' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:750:9: '(' expression ')'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal434=(Token)match(input,62,FOLLOW_62_in_parExpression4637); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal434_tree = 
            (CommonTree)adaptor.create(char_literal434)
            ;
            adaptor.addChild(root_0, char_literal434_tree);
            }

            pushFollow(FOLLOW_expression_in_parExpression4639);
            expression435=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression435.getTree());

            char_literal436=(Token)match(input,63,FOLLOW_63_in_parExpression4641); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal436_tree = 
            (CommonTree)adaptor.create(char_literal436)
            ;
            adaptor.addChild(root_0, char_literal436_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 101, parExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "parExpression"


    public static class expressionList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expressionList"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:753:1: expressionList : expression ( ',' expression )* ;
    public final JavaParser.expressionList_return expressionList() throws RecognitionException {
        JavaParser.expressionList_return retval = new JavaParser.expressionList_return();
        retval.start = input.LT(1);

        int expressionList_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal438=null;
        JavaParser.expression_return expression437 =null;

        JavaParser.expression_return expression439 =null;


        CommonTree char_literal438_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 102) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:754:5: ( expression ( ',' expression )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:754:9: expression ( ',' expression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_expression_in_expressionList4664);
            expression437=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression437.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:754:20: ( ',' expression )*
            loop124:
            do {
                int alt124=2;
                int LA124_0 = input.LA(1);

                if ( (LA124_0==68) ) {
                    alt124=1;
                }


                switch (alt124) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:754:21: ',' expression
            	    {
            	    char_literal438=(Token)match(input,68,FOLLOW_68_in_expressionList4667); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal438_tree = 
            	    (CommonTree)adaptor.create(char_literal438)
            	    ;
            	    adaptor.addChild(root_0, char_literal438_tree);
            	    }

            	    pushFollow(FOLLOW_expression_in_expressionList4669);
            	    expression439=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression439.getTree());

            	    }
            	    break;

            	default :
            	    break loop124;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 102, expressionList_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "expressionList"


    public static class statementExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statementExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:757:1: statementExpression : expression ;
    public final JavaParser.statementExpression_return statementExpression() throws RecognitionException {
        JavaParser.statementExpression_return retval = new JavaParser.statementExpression_return();
        retval.start = input.LT(1);

        int statementExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.expression_return expression440 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 103) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:758:5: ( expression )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:758:9: expression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_expression_in_statementExpression4690);
            expression440=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression440.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 103, statementExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "statementExpression"


    public static class constantExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "constantExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:761:1: constantExpression : expression ;
    public final JavaParser.constantExpression_return constantExpression() throws RecognitionException {
        JavaParser.constantExpression_return retval = new JavaParser.constantExpression_return();
        retval.start = input.LT(1);

        int constantExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.expression_return expression441 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 104) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:762:5: ( expression )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:762:9: expression
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_expression_in_constantExpression4713);
            expression441=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression441.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 104, constantExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "constantExpression"


    public static class expression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:765:1: expression : conditionalExpression ( assignmentOperator expression )? ;
    public final JavaParser.expression_return expression() throws RecognitionException {
        JavaParser.expression_return retval = new JavaParser.expression_return();
        retval.start = input.LT(1);

        int expression_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.conditionalExpression_return conditionalExpression442 =null;

        JavaParser.assignmentOperator_return assignmentOperator443 =null;

        JavaParser.expression_return expression444 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 105) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:766:5: ( conditionalExpression ( assignmentOperator expression )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:766:9: conditionalExpression ( assignmentOperator expression )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_conditionalExpression_in_expression4736);
            conditionalExpression442=conditionalExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalExpression442.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:766:31: ( assignmentOperator expression )?
            int alt125=2;
            switch ( input.LA(1) ) {
                case EQ:
                    {
                    int LA125_1 = input.LA(2);

                    if ( (synpred188_Java()) ) {
                        alt125=1;
                    }
                    }
                    break;
                case 67:
                    {
                    int LA125_2 = input.LA(2);

                    if ( (synpred188_Java()) ) {
                        alt125=1;
                    }
                    }
                    break;
                case 71:
                    {
                    int LA125_3 = input.LA(2);

                    if ( (synpred188_Java()) ) {
                        alt125=1;
                    }
                    }
                    break;
                case 64:
                    {
                    int LA125_4 = input.LA(2);

                    if ( (synpred188_Java()) ) {
                        alt125=1;
                    }
                    }
                    break;
                case 74:
                    {
                    int LA125_5 = input.LA(2);

                    if ( (synpred188_Java()) ) {
                        alt125=1;
                    }
                    }
                    break;
                case 61:
                    {
                    int LA125_6 = input.LA(2);

                    if ( (synpred188_Java()) ) {
                        alt125=1;
                    }
                    }
                    break;
                case 126:
                    {
                    int LA125_7 = input.LA(2);

                    if ( (synpred188_Java()) ) {
                        alt125=1;
                    }
                    }
                    break;
                case 82:
                    {
                    int LA125_8 = input.LA(2);

                    if ( (synpred188_Java()) ) {
                        alt125=1;
                    }
                    }
                    break;
                case 58:
                    {
                    int LA125_9 = input.LA(2);

                    if ( (synpred188_Java()) ) {
                        alt125=1;
                    }
                    }
                    break;
                case LS:
                    {
                    int LA125_10 = input.LA(2);

                    if ( (synpred188_Java()) ) {
                        alt125=1;
                    }
                    }
                    break;
                case GT:
                    {
                    int LA125_11 = input.LA(2);

                    if ( (synpred188_Java()) ) {
                        alt125=1;
                    }
                    }
                    break;
            }

            switch (alt125) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:766:32: assignmentOperator expression
                    {
                    pushFollow(FOLLOW_assignmentOperator_in_expression4739);
                    assignmentOperator443=assignmentOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, assignmentOperator443.getTree());

                    pushFollow(FOLLOW_expression_in_expression4741);
                    expression444=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression444.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 105, expression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "expression"


    public static class assignmentOperator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignmentOperator"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:769:1: assignmentOperator : ( '=' | '+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '%=' | ( '<' '<' '=' )=>t1= '<' t2= '<' t3= '=' {...}?| ( '>' '>' '>' '=' )=>t1= '>' t2= '>' t3= '>' t4= '=' {...}?| ( '>' '>' '=' )=>t1= '>' t2= '>' t3= '=' {...}?);
    public final JavaParser.assignmentOperator_return assignmentOperator() throws RecognitionException {
        JavaParser.assignmentOperator_return retval = new JavaParser.assignmentOperator_return();
        retval.start = input.LT(1);

        int assignmentOperator_StartIndex = input.index();

        CommonTree root_0 = null;

        Token t1=null;
        Token t2=null;
        Token t3=null;
        Token t4=null;
        Token char_literal445=null;
        Token string_literal446=null;
        Token string_literal447=null;
        Token string_literal448=null;
        Token string_literal449=null;
        Token string_literal450=null;
        Token string_literal451=null;
        Token string_literal452=null;
        Token string_literal453=null;

        CommonTree t1_tree=null;
        CommonTree t2_tree=null;
        CommonTree t3_tree=null;
        CommonTree t4_tree=null;
        CommonTree char_literal445_tree=null;
        CommonTree string_literal446_tree=null;
        CommonTree string_literal447_tree=null;
        CommonTree string_literal448_tree=null;
        CommonTree string_literal449_tree=null;
        CommonTree string_literal450_tree=null;
        CommonTree string_literal451_tree=null;
        CommonTree string_literal452_tree=null;
        CommonTree string_literal453_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 106) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:770:5: ( '=' | '+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '%=' | ( '<' '<' '=' )=>t1= '<' t2= '<' t3= '=' {...}?| ( '>' '>' '>' '=' )=>t1= '>' t2= '>' t3= '>' t4= '=' {...}?| ( '>' '>' '=' )=>t1= '>' t2= '>' t3= '=' {...}?)
            int alt126=12;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==EQ) ) {
                alt126=1;
            }
            else if ( (LA126_0==67) ) {
                alt126=2;
            }
            else if ( (LA126_0==71) ) {
                alt126=3;
            }
            else if ( (LA126_0==64) ) {
                alt126=4;
            }
            else if ( (LA126_0==74) ) {
                alt126=5;
            }
            else if ( (LA126_0==61) ) {
                alt126=6;
            }
            else if ( (LA126_0==126) ) {
                alt126=7;
            }
            else if ( (LA126_0==82) ) {
                alt126=8;
            }
            else if ( (LA126_0==58) ) {
                alt126=9;
            }
            else if ( (LA126_0==LS) && (synpred198_Java())) {
                alt126=10;
            }
            else if ( (LA126_0==GT) ) {
                int LA126_11 = input.LA(2);

                if ( (LA126_11==GT) ) {
                    int LA126_12 = input.LA(3);

                    if ( (LA126_12==GT) && (synpred199_Java())) {
                        alt126=11;
                    }
                    else if ( (LA126_12==EQ) && (synpred200_Java())) {
                        alt126=12;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 126, 12, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 126, 11, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 126, 0, input);

                throw nvae;

            }
            switch (alt126) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:770:9: '='
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal445=(Token)match(input,EQ,FOLLOW_EQ_in_assignmentOperator4766); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal445_tree = 
                    (CommonTree)adaptor.create(char_literal445)
                    ;
                    adaptor.addChild(root_0, char_literal445_tree);
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:771:9: '+='
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal446=(Token)match(input,67,FOLLOW_67_in_assignmentOperator4776); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal446_tree = 
                    (CommonTree)adaptor.create(string_literal446)
                    ;
                    adaptor.addChild(root_0, string_literal446_tree);
                    }

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:772:9: '-='
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal447=(Token)match(input,71,FOLLOW_71_in_assignmentOperator4786); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal447_tree = 
                    (CommonTree)adaptor.create(string_literal447)
                    ;
                    adaptor.addChild(root_0, string_literal447_tree);
                    }

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:773:9: '*='
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal448=(Token)match(input,64,FOLLOW_64_in_assignmentOperator4796); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal448_tree = 
                    (CommonTree)adaptor.create(string_literal448)
                    ;
                    adaptor.addChild(root_0, string_literal448_tree);
                    }

                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:774:9: '/='
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal449=(Token)match(input,74,FOLLOW_74_in_assignmentOperator4806); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal449_tree = 
                    (CommonTree)adaptor.create(string_literal449)
                    ;
                    adaptor.addChild(root_0, string_literal449_tree);
                    }

                    }
                    break;
                case 6 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:775:9: '&='
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal450=(Token)match(input,61,FOLLOW_61_in_assignmentOperator4816); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal450_tree = 
                    (CommonTree)adaptor.create(string_literal450)
                    ;
                    adaptor.addChild(root_0, string_literal450_tree);
                    }

                    }
                    break;
                case 7 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:776:9: '|='
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal451=(Token)match(input,126,FOLLOW_126_in_assignmentOperator4826); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal451_tree = 
                    (CommonTree)adaptor.create(string_literal451)
                    ;
                    adaptor.addChild(root_0, string_literal451_tree);
                    }

                    }
                    break;
                case 8 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:777:9: '^='
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal452=(Token)match(input,82,FOLLOW_82_in_assignmentOperator4836); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal452_tree = 
                    (CommonTree)adaptor.create(string_literal452)
                    ;
                    adaptor.addChild(root_0, string_literal452_tree);
                    }

                    }
                    break;
                case 9 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:778:9: '%='
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal453=(Token)match(input,58,FOLLOW_58_in_assignmentOperator4846); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal453_tree = 
                    (CommonTree)adaptor.create(string_literal453)
                    ;
                    adaptor.addChild(root_0, string_literal453_tree);
                    }

                    }
                    break;
                case 10 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:779:9: ( '<' '<' '=' )=>t1= '<' t2= '<' t3= '=' {...}?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    t1=(Token)match(input,LS,FOLLOW_LS_in_assignmentOperator4867); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t1_tree = 
                    (CommonTree)adaptor.create(t1)
                    ;
                    adaptor.addChild(root_0, t1_tree);
                    }

                    t2=(Token)match(input,LS,FOLLOW_LS_in_assignmentOperator4871); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t2_tree = 
                    (CommonTree)adaptor.create(t2)
                    ;
                    adaptor.addChild(root_0, t2_tree);
                    }

                    t3=(Token)match(input,EQ,FOLLOW_EQ_in_assignmentOperator4875); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t3_tree = 
                    (CommonTree)adaptor.create(t3)
                    ;
                    adaptor.addChild(root_0, t3_tree);
                    }

                    if ( !(( t1.getLine() == t2.getLine() &&
                              t1.getCharPositionInLine() + 1 == t2.getCharPositionInLine() && 
                              t2.getLine() == t3.getLine() && 
                              t2.getCharPositionInLine() + 1 == t3.getCharPositionInLine() )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "assignmentOperator", " $t1.getLine() == $t2.getLine() &&\r\n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() && \r\n          $t2.getLine() == $t3.getLine() && \r\n          $t2.getCharPositionInLine() + 1 == $t3.getCharPositionInLine() ");
                    }

                    }
                    break;
                case 11 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:784:9: ( '>' '>' '>' '=' )=>t1= '>' t2= '>' t3= '>' t4= '=' {...}?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    t1=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator4909); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t1_tree = 
                    (CommonTree)adaptor.create(t1)
                    ;
                    adaptor.addChild(root_0, t1_tree);
                    }

                    t2=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator4913); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t2_tree = 
                    (CommonTree)adaptor.create(t2)
                    ;
                    adaptor.addChild(root_0, t2_tree);
                    }

                    t3=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator4917); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t3_tree = 
                    (CommonTree)adaptor.create(t3)
                    ;
                    adaptor.addChild(root_0, t3_tree);
                    }

                    t4=(Token)match(input,EQ,FOLLOW_EQ_in_assignmentOperator4921); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t4_tree = 
                    (CommonTree)adaptor.create(t4)
                    ;
                    adaptor.addChild(root_0, t4_tree);
                    }

                    if ( !(( t1.getLine() == t2.getLine() && 
                              t1.getCharPositionInLine() + 1 == t2.getCharPositionInLine() &&
                              t2.getLine() == t3.getLine() && 
                              t2.getCharPositionInLine() + 1 == t3.getCharPositionInLine() &&
                              t3.getLine() == t4.getLine() && 
                              t3.getCharPositionInLine() + 1 == t4.getCharPositionInLine() )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "assignmentOperator", " $t1.getLine() == $t2.getLine() && \r\n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() &&\r\n          $t2.getLine() == $t3.getLine() && \r\n          $t2.getCharPositionInLine() + 1 == $t3.getCharPositionInLine() &&\r\n          $t3.getLine() == $t4.getLine() && \r\n          $t3.getCharPositionInLine() + 1 == $t4.getCharPositionInLine() ");
                    }

                    }
                    break;
                case 12 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:791:9: ( '>' '>' '=' )=>t1= '>' t2= '>' t3= '=' {...}?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    t1=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator4952); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t1_tree = 
                    (CommonTree)adaptor.create(t1)
                    ;
                    adaptor.addChild(root_0, t1_tree);
                    }

                    t2=(Token)match(input,GT,FOLLOW_GT_in_assignmentOperator4956); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t2_tree = 
                    (CommonTree)adaptor.create(t2)
                    ;
                    adaptor.addChild(root_0, t2_tree);
                    }

                    t3=(Token)match(input,EQ,FOLLOW_EQ_in_assignmentOperator4960); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t3_tree = 
                    (CommonTree)adaptor.create(t3)
                    ;
                    adaptor.addChild(root_0, t3_tree);
                    }

                    if ( !(( t1.getLine() == t2.getLine() && 
                              t1.getCharPositionInLine() + 1 == t2.getCharPositionInLine() && 
                              t2.getLine() == t3.getLine() && 
                              t2.getCharPositionInLine() + 1 == t3.getCharPositionInLine() )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "assignmentOperator", " $t1.getLine() == $t2.getLine() && \r\n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() && \r\n          $t2.getLine() == $t3.getLine() && \r\n          $t2.getCharPositionInLine() + 1 == $t3.getCharPositionInLine() ");
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 106, assignmentOperator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "assignmentOperator"


    public static class conditionalExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "conditionalExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:798:1: conditionalExpression : conditionalOrExpression ( '?' expression ':' expression )? ;
    public final JavaParser.conditionalExpression_return conditionalExpression() throws RecognitionException {
        JavaParser.conditionalExpression_return retval = new JavaParser.conditionalExpression_return();
        retval.start = input.LT(1);

        int conditionalExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal455=null;
        Token char_literal457=null;
        JavaParser.conditionalOrExpression_return conditionalOrExpression454 =null;

        JavaParser.expression_return expression456 =null;

        JavaParser.expression_return expression458 =null;


        CommonTree char_literal455_tree=null;
        CommonTree char_literal457_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 107) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:799:5: ( conditionalOrExpression ( '?' expression ':' expression )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:799:9: conditionalOrExpression ( '?' expression ':' expression )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_conditionalOrExpression_in_conditionalExpression4989);
            conditionalOrExpression454=conditionalOrExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalOrExpression454.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:799:33: ( '?' expression ':' expression )?
            int alt127=2;
            int LA127_0 = input.LA(1);

            if ( (LA127_0==77) ) {
                alt127=1;
            }
            switch (alt127) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:799:35: '?' expression ':' expression
                    {
                    char_literal455=(Token)match(input,77,FOLLOW_77_in_conditionalExpression4993); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal455_tree = 
                    (CommonTree)adaptor.create(char_literal455)
                    ;
                    adaptor.addChild(root_0, char_literal455_tree);
                    }

                    pushFollow(FOLLOW_expression_in_conditionalExpression4995);
                    expression456=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression456.getTree());

                    char_literal457=(Token)match(input,75,FOLLOW_75_in_conditionalExpression4997); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal457_tree = 
                    (CommonTree)adaptor.create(char_literal457)
                    ;
                    adaptor.addChild(root_0, char_literal457_tree);
                    }

                    pushFollow(FOLLOW_expression_in_conditionalExpression4999);
                    expression458=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression458.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 107, conditionalExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "conditionalExpression"


    public static class conditionalOrExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "conditionalOrExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:802:1: conditionalOrExpression : conditionalAndExpression ( '||' conditionalAndExpression )* ;
    public final JavaParser.conditionalOrExpression_return conditionalOrExpression() throws RecognitionException {
        JavaParser.conditionalOrExpression_return retval = new JavaParser.conditionalOrExpression_return();
        retval.start = input.LT(1);

        int conditionalOrExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal460=null;
        JavaParser.conditionalAndExpression_return conditionalAndExpression459 =null;

        JavaParser.conditionalAndExpression_return conditionalAndExpression461 =null;


        CommonTree string_literal460_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 108) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:803:5: ( conditionalAndExpression ( '||' conditionalAndExpression )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:803:9: conditionalAndExpression ( '||' conditionalAndExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_conditionalAndExpression_in_conditionalOrExpression5021);
            conditionalAndExpression459=conditionalAndExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalAndExpression459.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:803:34: ( '||' conditionalAndExpression )*
            loop128:
            do {
                int alt128=2;
                int LA128_0 = input.LA(1);

                if ( (LA128_0==127) ) {
                    alt128=1;
                }


                switch (alt128) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:803:36: '||' conditionalAndExpression
            	    {
            	    string_literal460=(Token)match(input,127,FOLLOW_127_in_conditionalOrExpression5025); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal460_tree = 
            	    (CommonTree)adaptor.create(string_literal460)
            	    ;
            	    adaptor.addChild(root_0, string_literal460_tree);
            	    }

            	    pushFollow(FOLLOW_conditionalAndExpression_in_conditionalOrExpression5027);
            	    conditionalAndExpression461=conditionalAndExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, conditionalAndExpression461.getTree());

            	    }
            	    break;

            	default :
            	    break loop128;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 108, conditionalOrExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "conditionalOrExpression"


    public static class conditionalAndExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "conditionalAndExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:806:1: conditionalAndExpression : inclusiveOrExpression ( '&&' inclusiveOrExpression )* ;
    public final JavaParser.conditionalAndExpression_return conditionalAndExpression() throws RecognitionException {
        JavaParser.conditionalAndExpression_return retval = new JavaParser.conditionalAndExpression_return();
        retval.start = input.LT(1);

        int conditionalAndExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal463=null;
        JavaParser.inclusiveOrExpression_return inclusiveOrExpression462 =null;

        JavaParser.inclusiveOrExpression_return inclusiveOrExpression464 =null;


        CommonTree string_literal463_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 109) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:807:5: ( inclusiveOrExpression ( '&&' inclusiveOrExpression )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:807:9: inclusiveOrExpression ( '&&' inclusiveOrExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_inclusiveOrExpression_in_conditionalAndExpression5049);
            inclusiveOrExpression462=inclusiveOrExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, inclusiveOrExpression462.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:807:31: ( '&&' inclusiveOrExpression )*
            loop129:
            do {
                int alt129=2;
                int LA129_0 = input.LA(1);

                if ( (LA129_0==59) ) {
                    alt129=1;
                }


                switch (alt129) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:807:33: '&&' inclusiveOrExpression
            	    {
            	    string_literal463=(Token)match(input,59,FOLLOW_59_in_conditionalAndExpression5053); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    string_literal463_tree = 
            	    (CommonTree)adaptor.create(string_literal463)
            	    ;
            	    adaptor.addChild(root_0, string_literal463_tree);
            	    }

            	    pushFollow(FOLLOW_inclusiveOrExpression_in_conditionalAndExpression5055);
            	    inclusiveOrExpression464=inclusiveOrExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, inclusiveOrExpression464.getTree());

            	    }
            	    break;

            	default :
            	    break loop129;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 109, conditionalAndExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "conditionalAndExpression"


    public static class inclusiveOrExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "inclusiveOrExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:810:1: inclusiveOrExpression : exclusiveOrExpression ( '|' exclusiveOrExpression )* ;
    public final JavaParser.inclusiveOrExpression_return inclusiveOrExpression() throws RecognitionException {
        JavaParser.inclusiveOrExpression_return retval = new JavaParser.inclusiveOrExpression_return();
        retval.start = input.LT(1);

        int inclusiveOrExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal466=null;
        JavaParser.exclusiveOrExpression_return exclusiveOrExpression465 =null;

        JavaParser.exclusiveOrExpression_return exclusiveOrExpression467 =null;


        CommonTree char_literal466_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 110) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:811:5: ( exclusiveOrExpression ( '|' exclusiveOrExpression )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:811:9: exclusiveOrExpression ( '|' exclusiveOrExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5077);
            exclusiveOrExpression465=exclusiveOrExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exclusiveOrExpression465.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:811:31: ( '|' exclusiveOrExpression )*
            loop130:
            do {
                int alt130=2;
                int LA130_0 = input.LA(1);

                if ( (LA130_0==125) ) {
                    alt130=1;
                }


                switch (alt130) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:811:33: '|' exclusiveOrExpression
            	    {
            	    char_literal466=(Token)match(input,125,FOLLOW_125_in_inclusiveOrExpression5081); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal466_tree = 
            	    (CommonTree)adaptor.create(char_literal466)
            	    ;
            	    adaptor.addChild(root_0, char_literal466_tree);
            	    }

            	    pushFollow(FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5083);
            	    exclusiveOrExpression467=exclusiveOrExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, exclusiveOrExpression467.getTree());

            	    }
            	    break;

            	default :
            	    break loop130;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 110, inclusiveOrExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "inclusiveOrExpression"


    public static class exclusiveOrExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "exclusiveOrExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:814:1: exclusiveOrExpression : andExpression ( '^' andExpression )* ;
    public final JavaParser.exclusiveOrExpression_return exclusiveOrExpression() throws RecognitionException {
        JavaParser.exclusiveOrExpression_return retval = new JavaParser.exclusiveOrExpression_return();
        retval.start = input.LT(1);

        int exclusiveOrExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal469=null;
        JavaParser.andExpression_return andExpression468 =null;

        JavaParser.andExpression_return andExpression470 =null;


        CommonTree char_literal469_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 111) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:815:5: ( andExpression ( '^' andExpression )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:815:9: andExpression ( '^' andExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression5105);
            andExpression468=andExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpression468.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:815:23: ( '^' andExpression )*
            loop131:
            do {
                int alt131=2;
                int LA131_0 = input.LA(1);

                if ( (LA131_0==81) ) {
                    alt131=1;
                }


                switch (alt131) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:815:25: '^' andExpression
            	    {
            	    char_literal469=(Token)match(input,81,FOLLOW_81_in_exclusiveOrExpression5109); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal469_tree = 
            	    (CommonTree)adaptor.create(char_literal469)
            	    ;
            	    adaptor.addChild(root_0, char_literal469_tree);
            	    }

            	    pushFollow(FOLLOW_andExpression_in_exclusiveOrExpression5111);
            	    andExpression470=andExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, andExpression470.getTree());

            	    }
            	    break;

            	default :
            	    break loop131;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 111, exclusiveOrExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "exclusiveOrExpression"


    public static class andExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "andExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:818:1: andExpression : equalityExpression ( '&' equalityExpression )* ;
    public final JavaParser.andExpression_return andExpression() throws RecognitionException {
        JavaParser.andExpression_return retval = new JavaParser.andExpression_return();
        retval.start = input.LT(1);

        int andExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal472=null;
        JavaParser.equalityExpression_return equalityExpression471 =null;

        JavaParser.equalityExpression_return equalityExpression473 =null;


        CommonTree char_literal472_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 112) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:819:5: ( equalityExpression ( '&' equalityExpression )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:819:9: equalityExpression ( '&' equalityExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_equalityExpression_in_andExpression5133);
            equalityExpression471=equalityExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, equalityExpression471.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:819:28: ( '&' equalityExpression )*
            loop132:
            do {
                int alt132=2;
                int LA132_0 = input.LA(1);

                if ( (LA132_0==60) ) {
                    alt132=1;
                }


                switch (alt132) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:819:30: '&' equalityExpression
            	    {
            	    char_literal472=(Token)match(input,60,FOLLOW_60_in_andExpression5137); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    char_literal472_tree = 
            	    (CommonTree)adaptor.create(char_literal472)
            	    ;
            	    adaptor.addChild(root_0, char_literal472_tree);
            	    }

            	    pushFollow(FOLLOW_equalityExpression_in_andExpression5139);
            	    equalityExpression473=equalityExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, equalityExpression473.getTree());

            	    }
            	    break;

            	default :
            	    break loop132;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 112, andExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "andExpression"


    public static class equalityExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "equalityExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:822:1: equalityExpression : instanceOfExpression ( ( '==' | '!=' ) instanceOfExpression )* ;
    public final JavaParser.equalityExpression_return equalityExpression() throws RecognitionException {
        JavaParser.equalityExpression_return retval = new JavaParser.equalityExpression_return();
        retval.start = input.LT(1);

        int equalityExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token set475=null;
        JavaParser.instanceOfExpression_return instanceOfExpression474 =null;

        JavaParser.instanceOfExpression_return instanceOfExpression476 =null;


        CommonTree set475_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 113) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:823:5: ( instanceOfExpression ( ( '==' | '!=' ) instanceOfExpression )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:823:9: instanceOfExpression ( ( '==' | '!=' ) instanceOfExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_instanceOfExpression_in_equalityExpression5161);
            instanceOfExpression474=instanceOfExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, instanceOfExpression474.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:823:30: ( ( '==' | '!=' ) instanceOfExpression )*
            loop133:
            do {
                int alt133=2;
                int LA133_0 = input.LA(1);

                if ( (LA133_0==56||LA133_0==76) ) {
                    alt133=1;
                }


                switch (alt133) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:823:32: ( '==' | '!=' ) instanceOfExpression
            	    {
            	    set475=(Token)input.LT(1);

            	    if ( input.LA(1)==56||input.LA(1)==76 ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
            	        (CommonTree)adaptor.create(set475)
            	        );
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_instanceOfExpression_in_equalityExpression5173);
            	    instanceOfExpression476=instanceOfExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, instanceOfExpression476.getTree());

            	    }
            	    break;

            	default :
            	    break loop133;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 113, equalityExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "equalityExpression"


    public static class instanceOfExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "instanceOfExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:826:1: instanceOfExpression : relationalExpression ( 'instanceof' type )? ;
    public final JavaParser.instanceOfExpression_return instanceOfExpression() throws RecognitionException {
        JavaParser.instanceOfExpression_return retval = new JavaParser.instanceOfExpression_return();
        retval.start = input.LT(1);

        int instanceOfExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal478=null;
        JavaParser.relationalExpression_return relationalExpression477 =null;

        JavaParser.type_return type479 =null;


        CommonTree string_literal478_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 114) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:827:5: ( relationalExpression ( 'instanceof' type )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:827:9: relationalExpression ( 'instanceof' type )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_relationalExpression_in_instanceOfExpression5195);
            relationalExpression477=relationalExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, relationalExpression477.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:827:30: ( 'instanceof' type )?
            int alt134=2;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==101) ) {
                alt134=1;
            }
            switch (alt134) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:827:31: 'instanceof' type
                    {
                    string_literal478=(Token)match(input,101,FOLLOW_101_in_instanceOfExpression5198); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal478_tree = 
                    (CommonTree)adaptor.create(string_literal478)
                    ;
                    adaptor.addChild(root_0, string_literal478_tree);
                    }

                    pushFollow(FOLLOW_type_in_instanceOfExpression5200);
                    type479=type();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, type479.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 114, instanceOfExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "instanceOfExpression"


    public static class relationalExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "relationalExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:830:1: relationalExpression : shiftExpression ( relationalOp shiftExpression )* ;
    public final JavaParser.relationalExpression_return relationalExpression() throws RecognitionException {
        JavaParser.relationalExpression_return retval = new JavaParser.relationalExpression_return();
        retval.start = input.LT(1);

        int relationalExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.shiftExpression_return shiftExpression480 =null;

        JavaParser.relationalOp_return relationalOp481 =null;

        JavaParser.shiftExpression_return shiftExpression482 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 115) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:831:5: ( shiftExpression ( relationalOp shiftExpression )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:831:9: shiftExpression ( relationalOp shiftExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_shiftExpression_in_relationalExpression5221);
            shiftExpression480=shiftExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, shiftExpression480.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:831:25: ( relationalOp shiftExpression )*
            loop135:
            do {
                int alt135=2;
                int LA135_0 = input.LA(1);

                if ( (LA135_0==LS) ) {
                    int LA135_2 = input.LA(2);

                    if ( (LA135_2==CharacterLiteral||LA135_2==DecimalLiteral||LA135_2==EQ||LA135_2==FloatingPointLiteral||LA135_2==HexLiteral||LA135_2==Identifier||LA135_2==OctalLiteral||(LA135_2 >= SUPER && LA135_2 <= StringLiteral)||LA135_2==VOID||LA135_2==55||LA135_2==62||(LA135_2 >= 65 && LA135_2 <= 66)||(LA135_2 >= 69 && LA135_2 <= 70)||LA135_2==84||LA135_2==86||LA135_2==89||LA135_2==93||LA135_2==95||LA135_2==98||LA135_2==102||LA135_2==104||(LA135_2 >= 106 && LA135_2 <= 107)||LA135_2==112||LA135_2==116||LA135_2==120||LA135_2==129) ) {
                        alt135=1;
                    }


                }
                else if ( (LA135_0==GT) ) {
                    int LA135_3 = input.LA(2);

                    if ( (LA135_3==CharacterLiteral||LA135_3==DecimalLiteral||LA135_3==EQ||LA135_3==FloatingPointLiteral||LA135_3==HexLiteral||LA135_3==Identifier||LA135_3==OctalLiteral||(LA135_3 >= SUPER && LA135_3 <= StringLiteral)||LA135_3==VOID||LA135_3==55||LA135_3==62||(LA135_3 >= 65 && LA135_3 <= 66)||(LA135_3 >= 69 && LA135_3 <= 70)||LA135_3==84||LA135_3==86||LA135_3==89||LA135_3==93||LA135_3==95||LA135_3==98||LA135_3==102||LA135_3==104||(LA135_3 >= 106 && LA135_3 <= 107)||LA135_3==112||LA135_3==116||LA135_3==120||LA135_3==129) ) {
                        alt135=1;
                    }


                }


                switch (alt135) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:831:27: relationalOp shiftExpression
            	    {
            	    pushFollow(FOLLOW_relationalOp_in_relationalExpression5225);
            	    relationalOp481=relationalOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, relationalOp481.getTree());

            	    pushFollow(FOLLOW_shiftExpression_in_relationalExpression5227);
            	    shiftExpression482=shiftExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, shiftExpression482.getTree());

            	    }
            	    break;

            	default :
            	    break loop135;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 115, relationalExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "relationalExpression"


    public static class relationalOp_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "relationalOp"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:834:1: relationalOp : ( ( LS EQ )=>t1= LS t2= EQ {...}?| ( GT EQ )=>t1= GT t2= EQ {...}?| LS | GT );
    public final JavaParser.relationalOp_return relationalOp() throws RecognitionException {
        JavaParser.relationalOp_return retval = new JavaParser.relationalOp_return();
        retval.start = input.LT(1);

        int relationalOp_StartIndex = input.index();

        CommonTree root_0 = null;

        Token t1=null;
        Token t2=null;
        Token LS483=null;
        Token GT484=null;

        CommonTree t1_tree=null;
        CommonTree t2_tree=null;
        CommonTree LS483_tree=null;
        CommonTree GT484_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 116) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:835:5: ( ( LS EQ )=>t1= LS t2= EQ {...}?| ( GT EQ )=>t1= GT t2= EQ {...}?| LS | GT )
            int alt136=4;
            int LA136_0 = input.LA(1);

            if ( (LA136_0==LS) ) {
                int LA136_1 = input.LA(2);

                if ( (LA136_1==EQ) && (synpred211_Java())) {
                    alt136=1;
                }
                else if ( (LA136_1==CharacterLiteral||LA136_1==DecimalLiteral||LA136_1==FloatingPointLiteral||LA136_1==HexLiteral||LA136_1==Identifier||LA136_1==OctalLiteral||(LA136_1 >= SUPER && LA136_1 <= StringLiteral)||LA136_1==VOID||LA136_1==55||LA136_1==62||(LA136_1 >= 65 && LA136_1 <= 66)||(LA136_1 >= 69 && LA136_1 <= 70)||LA136_1==84||LA136_1==86||LA136_1==89||LA136_1==93||LA136_1==95||LA136_1==98||LA136_1==102||LA136_1==104||(LA136_1 >= 106 && LA136_1 <= 107)||LA136_1==112||LA136_1==116||LA136_1==120||LA136_1==129) ) {
                    alt136=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 136, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA136_0==GT) ) {
                int LA136_2 = input.LA(2);

                if ( (LA136_2==EQ) && (synpred212_Java())) {
                    alt136=2;
                }
                else if ( (LA136_2==CharacterLiteral||LA136_2==DecimalLiteral||LA136_2==FloatingPointLiteral||LA136_2==HexLiteral||LA136_2==Identifier||LA136_2==OctalLiteral||(LA136_2 >= SUPER && LA136_2 <= StringLiteral)||LA136_2==VOID||LA136_2==55||LA136_2==62||(LA136_2 >= 65 && LA136_2 <= 66)||(LA136_2 >= 69 && LA136_2 <= 70)||LA136_2==84||LA136_2==86||LA136_2==89||LA136_2==93||LA136_2==95||LA136_2==98||LA136_2==102||LA136_2==104||(LA136_2 >= 106 && LA136_2 <= 107)||LA136_2==112||LA136_2==116||LA136_2==120||LA136_2==129) ) {
                    alt136=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 136, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 136, 0, input);

                throw nvae;

            }
            switch (alt136) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:835:9: ( LS EQ )=>t1= LS t2= EQ {...}?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    t1=(Token)match(input,LS,FOLLOW_LS_in_relationalOp5262); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t1_tree = 
                    (CommonTree)adaptor.create(t1)
                    ;
                    adaptor.addChild(root_0, t1_tree);
                    }

                    t2=(Token)match(input,EQ,FOLLOW_EQ_in_relationalOp5266); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t2_tree = 
                    (CommonTree)adaptor.create(t2)
                    ;
                    adaptor.addChild(root_0, t2_tree);
                    }

                    if ( !(( t1.getLine() == t2.getLine() && 
                              t1.getCharPositionInLine() + 1 == t2.getCharPositionInLine() )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "relationalOp", " $t1.getLine() == $t2.getLine() && \r\n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() ");
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:838:9: ( GT EQ )=>t1= GT t2= EQ {...}?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    t1=(Token)match(input,GT,FOLLOW_GT_in_relationalOp5296); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t1_tree = 
                    (CommonTree)adaptor.create(t1)
                    ;
                    adaptor.addChild(root_0, t1_tree);
                    }

                    t2=(Token)match(input,EQ,FOLLOW_EQ_in_relationalOp5300); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t2_tree = 
                    (CommonTree)adaptor.create(t2)
                    ;
                    adaptor.addChild(root_0, t2_tree);
                    }

                    if ( !(( t1.getLine() == t2.getLine() && 
                              t1.getCharPositionInLine() + 1 == t2.getCharPositionInLine() )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "relationalOp", " $t1.getLine() == $t2.getLine() && \r\n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() ");
                    }

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:841:9: LS
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    LS483=(Token)match(input,LS,FOLLOW_LS_in_relationalOp5321); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LS483_tree = 
                    (CommonTree)adaptor.create(LS483)
                    ;
                    adaptor.addChild(root_0, LS483_tree);
                    }

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:842:9: GT
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    GT484=(Token)match(input,GT,FOLLOW_GT_in_relationalOp5332); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GT484_tree = 
                    (CommonTree)adaptor.create(GT484)
                    ;
                    adaptor.addChild(root_0, GT484_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 116, relationalOp_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "relationalOp"


    public static class shiftExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "shiftExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:845:1: shiftExpression : additiveExpression ( shiftOp additiveExpression )* ;
    public final JavaParser.shiftExpression_return shiftExpression() throws RecognitionException {
        JavaParser.shiftExpression_return retval = new JavaParser.shiftExpression_return();
        retval.start = input.LT(1);

        int shiftExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.additiveExpression_return additiveExpression485 =null;

        JavaParser.shiftOp_return shiftOp486 =null;

        JavaParser.additiveExpression_return additiveExpression487 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 117) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:846:5: ( additiveExpression ( shiftOp additiveExpression )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:846:9: additiveExpression ( shiftOp additiveExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_additiveExpression_in_shiftExpression5352);
            additiveExpression485=additiveExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, additiveExpression485.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:846:28: ( shiftOp additiveExpression )*
            loop137:
            do {
                int alt137=2;
                int LA137_0 = input.LA(1);

                if ( (LA137_0==LS) ) {
                    int LA137_1 = input.LA(2);

                    if ( (LA137_1==LS) ) {
                        int LA137_4 = input.LA(3);

                        if ( (LA137_4==CharacterLiteral||LA137_4==DecimalLiteral||LA137_4==FloatingPointLiteral||LA137_4==HexLiteral||LA137_4==Identifier||LA137_4==OctalLiteral||(LA137_4 >= SUPER && LA137_4 <= StringLiteral)||LA137_4==VOID||LA137_4==55||LA137_4==62||(LA137_4 >= 65 && LA137_4 <= 66)||(LA137_4 >= 69 && LA137_4 <= 70)||LA137_4==84||LA137_4==86||LA137_4==89||LA137_4==93||LA137_4==95||LA137_4==98||LA137_4==102||LA137_4==104||(LA137_4 >= 106 && LA137_4 <= 107)||LA137_4==112||LA137_4==116||LA137_4==120||LA137_4==129) ) {
                            alt137=1;
                        }


                    }


                }
                else if ( (LA137_0==GT) ) {
                    int LA137_2 = input.LA(2);

                    if ( (LA137_2==GT) ) {
                        int LA137_5 = input.LA(3);

                        if ( (LA137_5==GT) ) {
                            int LA137_7 = input.LA(4);

                            if ( (LA137_7==CharacterLiteral||LA137_7==DecimalLiteral||LA137_7==FloatingPointLiteral||LA137_7==HexLiteral||LA137_7==Identifier||LA137_7==OctalLiteral||(LA137_7 >= SUPER && LA137_7 <= StringLiteral)||LA137_7==VOID||LA137_7==55||LA137_7==62||(LA137_7 >= 65 && LA137_7 <= 66)||(LA137_7 >= 69 && LA137_7 <= 70)||LA137_7==84||LA137_7==86||LA137_7==89||LA137_7==93||LA137_7==95||LA137_7==98||LA137_7==102||LA137_7==104||(LA137_7 >= 106 && LA137_7 <= 107)||LA137_7==112||LA137_7==116||LA137_7==120||LA137_7==129) ) {
                                alt137=1;
                            }


                        }
                        else if ( (LA137_5==CharacterLiteral||LA137_5==DecimalLiteral||LA137_5==FloatingPointLiteral||LA137_5==HexLiteral||LA137_5==Identifier||LA137_5==OctalLiteral||(LA137_5 >= SUPER && LA137_5 <= StringLiteral)||LA137_5==VOID||LA137_5==55||LA137_5==62||(LA137_5 >= 65 && LA137_5 <= 66)||(LA137_5 >= 69 && LA137_5 <= 70)||LA137_5==84||LA137_5==86||LA137_5==89||LA137_5==93||LA137_5==95||LA137_5==98||LA137_5==102||LA137_5==104||(LA137_5 >= 106 && LA137_5 <= 107)||LA137_5==112||LA137_5==116||LA137_5==120||LA137_5==129) ) {
                            alt137=1;
                        }


                    }


                }


                switch (alt137) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:846:30: shiftOp additiveExpression
            	    {
            	    pushFollow(FOLLOW_shiftOp_in_shiftExpression5356);
            	    shiftOp486=shiftOp();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, shiftOp486.getTree());

            	    pushFollow(FOLLOW_additiveExpression_in_shiftExpression5358);
            	    additiveExpression487=additiveExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, additiveExpression487.getTree());

            	    }
            	    break;

            	default :
            	    break loop137;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 117, shiftExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "shiftExpression"


    public static class shiftOp_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "shiftOp"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:849:1: shiftOp : ( ( '<' '<' )=>t1= '<' t2= '<' {...}?| ( '>' '>' '>' )=>t1= '>' t2= '>' t3= '>' {...}?| ( '>' '>' )=>t1= '>' t2= '>' {...}?);
    public final JavaParser.shiftOp_return shiftOp() throws RecognitionException {
        JavaParser.shiftOp_return retval = new JavaParser.shiftOp_return();
        retval.start = input.LT(1);

        int shiftOp_StartIndex = input.index();

        CommonTree root_0 = null;

        Token t1=null;
        Token t2=null;
        Token t3=null;

        CommonTree t1_tree=null;
        CommonTree t2_tree=null;
        CommonTree t3_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 118) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:850:5: ( ( '<' '<' )=>t1= '<' t2= '<' {...}?| ( '>' '>' '>' )=>t1= '>' t2= '>' t3= '>' {...}?| ( '>' '>' )=>t1= '>' t2= '>' {...}?)
            int alt138=3;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==LS) && (synpred215_Java())) {
                alt138=1;
            }
            else if ( (LA138_0==GT) ) {
                int LA138_2 = input.LA(2);

                if ( (LA138_2==GT) ) {
                    int LA138_3 = input.LA(3);

                    if ( (LA138_3==GT) && (synpred216_Java())) {
                        alt138=2;
                    }
                    else if ( (LA138_3==65) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==69) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==66) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==70) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==129) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==55) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==62) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==116) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==SUPER) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==DecimalLiteral||LA138_3==HexLiteral||LA138_3==OctalLiteral) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==FloatingPointLiteral) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==CharacterLiteral) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==StringLiteral) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==95||LA138_3==120) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==107) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==106) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==Identifier) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==84||LA138_3==86||LA138_3==89||LA138_3==93||LA138_3==98||LA138_3==102||LA138_3==104||LA138_3==112) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else if ( (LA138_3==VOID) && (synpred217_Java())) {
                        alt138=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 138, 3, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 138, 2, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 138, 0, input);

                throw nvae;

            }
            switch (alt138) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:850:9: ( '<' '<' )=>t1= '<' t2= '<' {...}?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    t1=(Token)match(input,LS,FOLLOW_LS_in_shiftOp5389); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t1_tree = 
                    (CommonTree)adaptor.create(t1)
                    ;
                    adaptor.addChild(root_0, t1_tree);
                    }

                    t2=(Token)match(input,LS,FOLLOW_LS_in_shiftOp5393); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t2_tree = 
                    (CommonTree)adaptor.create(t2)
                    ;
                    adaptor.addChild(root_0, t2_tree);
                    }

                    if ( !(( t1.getLine() == t2.getLine() && 
                              t1.getCharPositionInLine() + 1 == t2.getCharPositionInLine() )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "shiftOp", " $t1.getLine() == $t2.getLine() && \r\n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() ");
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:853:9: ( '>' '>' '>' )=>t1= '>' t2= '>' t3= '>' {...}?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    t1=(Token)match(input,GT,FOLLOW_GT_in_shiftOp5425); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t1_tree = 
                    (CommonTree)adaptor.create(t1)
                    ;
                    adaptor.addChild(root_0, t1_tree);
                    }

                    t2=(Token)match(input,GT,FOLLOW_GT_in_shiftOp5429); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t2_tree = 
                    (CommonTree)adaptor.create(t2)
                    ;
                    adaptor.addChild(root_0, t2_tree);
                    }

                    t3=(Token)match(input,GT,FOLLOW_GT_in_shiftOp5433); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t3_tree = 
                    (CommonTree)adaptor.create(t3)
                    ;
                    adaptor.addChild(root_0, t3_tree);
                    }

                    if ( !(( t1.getLine() == t2.getLine() && 
                              t1.getCharPositionInLine() + 1 == t2.getCharPositionInLine() &&
                              t2.getLine() == t3.getLine() && 
                              t2.getCharPositionInLine() + 1 == t3.getCharPositionInLine() )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "shiftOp", " $t1.getLine() == $t2.getLine() && \r\n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() &&\r\n          $t2.getLine() == $t3.getLine() && \r\n          $t2.getCharPositionInLine() + 1 == $t3.getCharPositionInLine() ");
                    }

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:858:9: ( '>' '>' )=>t1= '>' t2= '>' {...}?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    t1=(Token)match(input,GT,FOLLOW_GT_in_shiftOp5463); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t1_tree = 
                    (CommonTree)adaptor.create(t1)
                    ;
                    adaptor.addChild(root_0, t1_tree);
                    }

                    t2=(Token)match(input,GT,FOLLOW_GT_in_shiftOp5467); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t2_tree = 
                    (CommonTree)adaptor.create(t2)
                    ;
                    adaptor.addChild(root_0, t2_tree);
                    }

                    if ( !(( t1.getLine() == t2.getLine() && 
                              t1.getCharPositionInLine() + 1 == t2.getCharPositionInLine() )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "shiftOp", " $t1.getLine() == $t2.getLine() && \r\n          $t1.getCharPositionInLine() + 1 == $t2.getCharPositionInLine() ");
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 118, shiftOp_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "shiftOp"


    public static class additiveExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "additiveExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:864:1: additiveExpression : multiplicativeExpression ( ( '+' | '-' ) multiplicativeExpression )* ;
    public final JavaParser.additiveExpression_return additiveExpression() throws RecognitionException {
        JavaParser.additiveExpression_return retval = new JavaParser.additiveExpression_return();
        retval.start = input.LT(1);

        int additiveExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token set489=null;
        JavaParser.multiplicativeExpression_return multiplicativeExpression488 =null;

        JavaParser.multiplicativeExpression_return multiplicativeExpression490 =null;


        CommonTree set489_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 119) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:865:5: ( multiplicativeExpression ( ( '+' | '-' ) multiplicativeExpression )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:865:9: multiplicativeExpression ( ( '+' | '-' ) multiplicativeExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression5497);
            multiplicativeExpression488=multiplicativeExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicativeExpression488.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:865:34: ( ( '+' | '-' ) multiplicativeExpression )*
            loop139:
            do {
                int alt139=2;
                int LA139_0 = input.LA(1);

                if ( (LA139_0==65||LA139_0==69) ) {
                    alt139=1;
                }


                switch (alt139) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:865:36: ( '+' | '-' ) multiplicativeExpression
            	    {
            	    set489=(Token)input.LT(1);

            	    if ( input.LA(1)==65||input.LA(1)==69 ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
            	        (CommonTree)adaptor.create(set489)
            	        );
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_multiplicativeExpression_in_additiveExpression5509);
            	    multiplicativeExpression490=multiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, multiplicativeExpression490.getTree());

            	    }
            	    break;

            	default :
            	    break loop139;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 119, additiveExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "additiveExpression"


    public static class multiplicativeExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multiplicativeExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:868:1: multiplicativeExpression : unaryExpression ( ( '*' | '/' | '%' ) unaryExpression )* ;
    public final JavaParser.multiplicativeExpression_return multiplicativeExpression() throws RecognitionException {
        JavaParser.multiplicativeExpression_return retval = new JavaParser.multiplicativeExpression_return();
        retval.start = input.LT(1);

        int multiplicativeExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token set492=null;
        JavaParser.unaryExpression_return unaryExpression491 =null;

        JavaParser.unaryExpression_return unaryExpression493 =null;


        CommonTree set492_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 120) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:869:5: ( unaryExpression ( ( '*' | '/' | '%' ) unaryExpression )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:869:9: unaryExpression ( ( '*' | '/' | '%' ) unaryExpression )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression5531);
            unaryExpression491=unaryExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression491.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:869:25: ( ( '*' | '/' | '%' ) unaryExpression )*
            loop140:
            do {
                int alt140=2;
                int LA140_0 = input.LA(1);

                if ( (LA140_0==STAR||LA140_0==57||LA140_0==73) ) {
                    alt140=1;
                }


                switch (alt140) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:869:27: ( '*' | '/' | '%' ) unaryExpression
            	    {
            	    set492=(Token)input.LT(1);

            	    if ( input.LA(1)==STAR||input.LA(1)==57||input.LA(1)==73 ) {
            	        input.consume();
            	        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
            	        (CommonTree)adaptor.create(set492)
            	        );
            	        state.errorRecovery=false;
            	        state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }


            	    pushFollow(FOLLOW_unaryExpression_in_multiplicativeExpression5549);
            	    unaryExpression493=unaryExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression493.getTree());

            	    }
            	    break;

            	default :
            	    break loop140;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 120, multiplicativeExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "multiplicativeExpression"


    public static class unaryExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:872:1: unaryExpression : ( '+' unaryExpression | '-' unaryExpression | '++' unaryExpression | '--' unaryExpression | unaryExpressionNotPlusMinus );
    public final JavaParser.unaryExpression_return unaryExpression() throws RecognitionException {
        JavaParser.unaryExpression_return retval = new JavaParser.unaryExpression_return();
        retval.start = input.LT(1);

        int unaryExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal494=null;
        Token char_literal496=null;
        Token string_literal498=null;
        Token string_literal500=null;
        JavaParser.unaryExpression_return unaryExpression495 =null;

        JavaParser.unaryExpression_return unaryExpression497 =null;

        JavaParser.unaryExpression_return unaryExpression499 =null;

        JavaParser.unaryExpression_return unaryExpression501 =null;

        JavaParser.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus502 =null;


        CommonTree char_literal494_tree=null;
        CommonTree char_literal496_tree=null;
        CommonTree string_literal498_tree=null;
        CommonTree string_literal500_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 121) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:873:5: ( '+' unaryExpression | '-' unaryExpression | '++' unaryExpression | '--' unaryExpression | unaryExpressionNotPlusMinus )
            int alt141=5;
            switch ( input.LA(1) ) {
            case 65:
                {
                alt141=1;
                }
                break;
            case 69:
                {
                alt141=2;
                }
                break;
            case 66:
                {
                alt141=3;
                }
                break;
            case 70:
                {
                alt141=4;
                }
                break;
            case CharacterLiteral:
            case DecimalLiteral:
            case FloatingPointLiteral:
            case HexLiteral:
            case Identifier:
            case OctalLiteral:
            case SUPER:
            case StringLiteral:
            case VOID:
            case 55:
            case 62:
            case 84:
            case 86:
            case 89:
            case 93:
            case 95:
            case 98:
            case 102:
            case 104:
            case 106:
            case 107:
            case 112:
            case 116:
            case 120:
            case 129:
                {
                alt141=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 141, 0, input);

                throw nvae;

            }

            switch (alt141) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:873:9: '+' unaryExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal494=(Token)match(input,65,FOLLOW_65_in_unaryExpression5575); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal494_tree = 
                    (CommonTree)adaptor.create(char_literal494)
                    ;
                    adaptor.addChild(root_0, char_literal494_tree);
                    }

                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression5577);
                    unaryExpression495=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression495.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:874:9: '-' unaryExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal496=(Token)match(input,69,FOLLOW_69_in_unaryExpression5587); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal496_tree = 
                    (CommonTree)adaptor.create(char_literal496)
                    ;
                    adaptor.addChild(root_0, char_literal496_tree);
                    }

                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression5589);
                    unaryExpression497=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression497.getTree());

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:875:9: '++' unaryExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal498=(Token)match(input,66,FOLLOW_66_in_unaryExpression5599); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal498_tree = 
                    (CommonTree)adaptor.create(string_literal498)
                    ;
                    adaptor.addChild(root_0, string_literal498_tree);
                    }

                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression5601);
                    unaryExpression499=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression499.getTree());

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:876:9: '--' unaryExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal500=(Token)match(input,70,FOLLOW_70_in_unaryExpression5611); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal500_tree = 
                    (CommonTree)adaptor.create(string_literal500)
                    ;
                    adaptor.addChild(root_0, string_literal500_tree);
                    }

                    pushFollow(FOLLOW_unaryExpression_in_unaryExpression5613);
                    unaryExpression501=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression501.getTree());

                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:877:9: unaryExpressionNotPlusMinus
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression5623);
                    unaryExpressionNotPlusMinus502=unaryExpressionNotPlusMinus();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpressionNotPlusMinus502.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 121, unaryExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "unaryExpression"


    public static class unaryExpressionNotPlusMinus_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryExpressionNotPlusMinus"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:880:1: unaryExpressionNotPlusMinus : ( '~' unaryExpression | '!' unaryExpression | castExpression | primary ( selector )* ( '++' | '--' )? );
    public final JavaParser.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus() throws RecognitionException {
        JavaParser.unaryExpressionNotPlusMinus_return retval = new JavaParser.unaryExpressionNotPlusMinus_return();
        retval.start = input.LT(1);

        int unaryExpressionNotPlusMinus_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal503=null;
        Token char_literal505=null;
        Token set510=null;
        JavaParser.unaryExpression_return unaryExpression504 =null;

        JavaParser.unaryExpression_return unaryExpression506 =null;

        JavaParser.castExpression_return castExpression507 =null;

        JavaParser.primary_return primary508 =null;

        JavaParser.selector_return selector509 =null;


        CommonTree char_literal503_tree=null;
        CommonTree char_literal505_tree=null;
        CommonTree set510_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 122) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:881:5: ( '~' unaryExpression | '!' unaryExpression | castExpression | primary ( selector )* ( '++' | '--' )? )
            int alt144=4;
            switch ( input.LA(1) ) {
            case 129:
                {
                alt144=1;
                }
                break;
            case 55:
                {
                alt144=2;
                }
                break;
            case 62:
                {
                int LA144_3 = input.LA(2);

                if ( (synpred229_Java()) ) {
                    alt144=3;
                }
                else if ( (true) ) {
                    alt144=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 144, 3, input);

                    throw nvae;

                }
                }
                break;
            case CharacterLiteral:
            case DecimalLiteral:
            case FloatingPointLiteral:
            case HexLiteral:
            case Identifier:
            case OctalLiteral:
            case SUPER:
            case StringLiteral:
            case VOID:
            case 84:
            case 86:
            case 89:
            case 93:
            case 95:
            case 98:
            case 102:
            case 104:
            case 106:
            case 107:
            case 112:
            case 116:
            case 120:
                {
                alt144=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 144, 0, input);

                throw nvae;

            }

            switch (alt144) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:881:9: '~' unaryExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal503=(Token)match(input,129,FOLLOW_129_in_unaryExpressionNotPlusMinus5642); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal503_tree = 
                    (CommonTree)adaptor.create(char_literal503)
                    ;
                    adaptor.addChild(root_0, char_literal503_tree);
                    }

                    pushFollow(FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus5644);
                    unaryExpression504=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression504.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:882:9: '!' unaryExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal505=(Token)match(input,55,FOLLOW_55_in_unaryExpressionNotPlusMinus5654); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal505_tree = 
                    (CommonTree)adaptor.create(char_literal505)
                    ;
                    adaptor.addChild(root_0, char_literal505_tree);
                    }

                    pushFollow(FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus5656);
                    unaryExpression506=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression506.getTree());

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:883:9: castExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_castExpression_in_unaryExpressionNotPlusMinus5666);
                    castExpression507=castExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, castExpression507.getTree());

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:884:9: primary ( selector )* ( '++' | '--' )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_primary_in_unaryExpressionNotPlusMinus5676);
                    primary508=primary();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primary508.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:884:17: ( selector )*
                    loop142:
                    do {
                        int alt142=2;
                        int LA142_0 = input.LA(1);

                        if ( (LA142_0==DOT||LA142_0==79) ) {
                            alt142=1;
                        }


                        switch (alt142) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:884:17: selector
                    	    {
                    	    pushFollow(FOLLOW_selector_in_unaryExpressionNotPlusMinus5678);
                    	    selector509=selector();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, selector509.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop142;
                        }
                    } while (true);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:884:27: ( '++' | '--' )?
                    int alt143=2;
                    int LA143_0 = input.LA(1);

                    if ( (LA143_0==66||LA143_0==70) ) {
                        alt143=1;
                    }
                    switch (alt143) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                            {
                            set510=(Token)input.LT(1);

                            if ( input.LA(1)==66||input.LA(1)==70 ) {
                                input.consume();
                                if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                                (CommonTree)adaptor.create(set510)
                                );
                                state.errorRecovery=false;
                                state.failed=false;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 122, unaryExpressionNotPlusMinus_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "unaryExpressionNotPlusMinus"


    public static class castExpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "castExpression"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:887:1: castExpression : ( '(' primitiveType ')' unaryExpression | '(' ( type | expression ) ')' unaryExpressionNotPlusMinus );
    public final JavaParser.castExpression_return castExpression() throws RecognitionException {
        JavaParser.castExpression_return retval = new JavaParser.castExpression_return();
        retval.start = input.LT(1);

        int castExpression_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal511=null;
        Token char_literal513=null;
        Token char_literal515=null;
        Token char_literal518=null;
        JavaParser.primitiveType_return primitiveType512 =null;

        JavaParser.unaryExpression_return unaryExpression514 =null;

        JavaParser.type_return type516 =null;

        JavaParser.expression_return expression517 =null;

        JavaParser.unaryExpressionNotPlusMinus_return unaryExpressionNotPlusMinus519 =null;


        CommonTree char_literal511_tree=null;
        CommonTree char_literal513_tree=null;
        CommonTree char_literal515_tree=null;
        CommonTree char_literal518_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 123) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:888:5: ( '(' primitiveType ')' unaryExpression | '(' ( type | expression ) ')' unaryExpressionNotPlusMinus )
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==62) ) {
                int LA146_1 = input.LA(2);

                if ( (synpred233_Java()) ) {
                    alt146=1;
                }
                else if ( (true) ) {
                    alt146=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 146, 1, input);

                    throw nvae;

                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 146, 0, input);

                throw nvae;

            }
            switch (alt146) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:888:8: '(' primitiveType ')' unaryExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal511=(Token)match(input,62,FOLLOW_62_in_castExpression5704); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal511_tree = 
                    (CommonTree)adaptor.create(char_literal511)
                    ;
                    adaptor.addChild(root_0, char_literal511_tree);
                    }

                    pushFollow(FOLLOW_primitiveType_in_castExpression5706);
                    primitiveType512=primitiveType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primitiveType512.getTree());

                    char_literal513=(Token)match(input,63,FOLLOW_63_in_castExpression5708); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal513_tree = 
                    (CommonTree)adaptor.create(char_literal513)
                    ;
                    adaptor.addChild(root_0, char_literal513_tree);
                    }

                    pushFollow(FOLLOW_unaryExpression_in_castExpression5710);
                    unaryExpression514=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression514.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:889:8: '(' ( type | expression ) ')' unaryExpressionNotPlusMinus
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal515=(Token)match(input,62,FOLLOW_62_in_castExpression5719); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal515_tree = 
                    (CommonTree)adaptor.create(char_literal515)
                    ;
                    adaptor.addChild(root_0, char_literal515_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:889:12: ( type | expression )
                    int alt145=2;
                    alt145 = dfa145.predict(input);
                    switch (alt145) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:889:13: type
                            {
                            pushFollow(FOLLOW_type_in_castExpression5722);
                            type516=type();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, type516.getTree());

                            }
                            break;
                        case 2 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:889:20: expression
                            {
                            pushFollow(FOLLOW_expression_in_castExpression5726);
                            expression517=expression();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, expression517.getTree());

                            }
                            break;

                    }


                    char_literal518=(Token)match(input,63,FOLLOW_63_in_castExpression5729); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal518_tree = 
                    (CommonTree)adaptor.create(char_literal518)
                    ;
                    adaptor.addChild(root_0, char_literal518_tree);
                    }

                    pushFollow(FOLLOW_unaryExpressionNotPlusMinus_in_castExpression5731);
                    unaryExpressionNotPlusMinus519=unaryExpressionNotPlusMinus();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpressionNotPlusMinus519.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 123, castExpression_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "castExpression"


    public static class primary_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "primary"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:892:1: primary : ( parExpression | 'this' ( '.' Identifier )* ( identifierSuffix )? -> ^( PRIMARY 'this' ( '.' Identifier )* ( identifierSuffix )? ) | SUPER superSuffix -> ^( PRIMARY SUPER superSuffix ) | literal | 'new' creator | Identifier ( '.' Identifier )* ( identifierSuffix )? -> ^( PRIMARY Identifier ( '.' Identifier )* ( identifierSuffix )? ) | primitiveType ( '[' ']' )* '.' 'class' | 'void' '.' 'class' );
    public final JavaParser.primary_return primary() throws RecognitionException {
        JavaParser.primary_return retval = new JavaParser.primary_return();
        retval.start = input.LT(1);

        int primary_StartIndex = input.index();

        CommonTree root_0 = null;

        Token string_literal521=null;
        Token char_literal522=null;
        Token Identifier523=null;
        Token SUPER525=null;
        Token string_literal528=null;
        Token Identifier530=null;
        Token char_literal531=null;
        Token Identifier532=null;
        Token char_literal535=null;
        Token char_literal536=null;
        Token char_literal537=null;
        Token string_literal538=null;
        Token string_literal539=null;
        Token char_literal540=null;
        Token string_literal541=null;
        JavaParser.parExpression_return parExpression520 =null;

        JavaParser.identifierSuffix_return identifierSuffix524 =null;

        JavaParser.superSuffix_return superSuffix526 =null;

        JavaParser.literal_return literal527 =null;

        JavaParser.creator_return creator529 =null;

        JavaParser.identifierSuffix_return identifierSuffix533 =null;

        JavaParser.primitiveType_return primitiveType534 =null;


        CommonTree string_literal521_tree=null;
        CommonTree char_literal522_tree=null;
        CommonTree Identifier523_tree=null;
        CommonTree SUPER525_tree=null;
        CommonTree string_literal528_tree=null;
        CommonTree Identifier530_tree=null;
        CommonTree char_literal531_tree=null;
        CommonTree Identifier532_tree=null;
        CommonTree char_literal535_tree=null;
        CommonTree char_literal536_tree=null;
        CommonTree char_literal537_tree=null;
        CommonTree string_literal538_tree=null;
        CommonTree string_literal539_tree=null;
        CommonTree char_literal540_tree=null;
        CommonTree string_literal541_tree=null;
        RewriteRuleTokenStream stream_116=new RewriteRuleTokenStream(adaptor,"token 116");
        RewriteRuleTokenStream stream_SUPER=new RewriteRuleTokenStream(adaptor,"token SUPER");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_Identifier=new RewriteRuleTokenStream(adaptor,"token Identifier");
        RewriteRuleSubtreeStream stream_superSuffix=new RewriteRuleSubtreeStream(adaptor,"rule superSuffix");
        RewriteRuleSubtreeStream stream_identifierSuffix=new RewriteRuleSubtreeStream(adaptor,"rule identifierSuffix");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 124) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:893:5: ( parExpression | 'this' ( '.' Identifier )* ( identifierSuffix )? -> ^( PRIMARY 'this' ( '.' Identifier )* ( identifierSuffix )? ) | SUPER superSuffix -> ^( PRIMARY SUPER superSuffix ) | literal | 'new' creator | Identifier ( '.' Identifier )* ( identifierSuffix )? -> ^( PRIMARY Identifier ( '.' Identifier )* ( identifierSuffix )? ) | primitiveType ( '[' ']' )* '.' 'class' | 'void' '.' 'class' )
            int alt152=8;
            switch ( input.LA(1) ) {
            case 62:
                {
                alt152=1;
                }
                break;
            case 116:
                {
                alt152=2;
                }
                break;
            case SUPER:
                {
                alt152=3;
                }
                break;
            case CharacterLiteral:
            case DecimalLiteral:
            case FloatingPointLiteral:
            case HexLiteral:
            case OctalLiteral:
            case StringLiteral:
            case 95:
            case 107:
            case 120:
                {
                alt152=4;
                }
                break;
            case 106:
                {
                alt152=5;
                }
                break;
            case Identifier:
                {
                alt152=6;
                }
                break;
            case 84:
            case 86:
            case 89:
            case 93:
            case 98:
            case 102:
            case 104:
            case 112:
                {
                alt152=7;
                }
                break;
            case VOID:
                {
                alt152=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 152, 0, input);

                throw nvae;

            }

            switch (alt152) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:893:9: parExpression
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_parExpression_in_primary5750);
                    parExpression520=parExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parExpression520.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:9: 'this' ( '.' Identifier )* ( identifierSuffix )?
                    {
                    string_literal521=(Token)match(input,116,FOLLOW_116_in_primary5760); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_116.add(string_literal521);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:16: ( '.' Identifier )*
                    loop147:
                    do {
                        int alt147=2;
                        int LA147_0 = input.LA(1);

                        if ( (LA147_0==DOT) ) {
                            int LA147_2 = input.LA(2);

                            if ( (LA147_2==Identifier) ) {
                                int LA147_3 = input.LA(3);

                                if ( (synpred236_Java()) ) {
                                    alt147=1;
                                }


                            }


                        }


                        switch (alt147) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:17: '.' Identifier
                    	    {
                    	    char_literal522=(Token)match(input,DOT,FOLLOW_DOT_in_primary5763); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_DOT.add(char_literal522);


                    	    Identifier523=(Token)match(input,Identifier,FOLLOW_Identifier_in_primary5765); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_Identifier.add(Identifier523);


                    	    }
                    	    break;

                    	default :
                    	    break loop147;
                        }
                    } while (true);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:34: ( identifierSuffix )?
                    int alt148=2;
                    switch ( input.LA(1) ) {
                        case 79:
                            {
                            int LA148_1 = input.LA(2);

                            if ( (synpred237_Java()) ) {
                                alt148=1;
                            }
                            }
                            break;
                        case 62:
                            {
                            alt148=1;
                            }
                            break;
                        case DOT:
                            {
                            int LA148_3 = input.LA(2);

                            if ( (synpred237_Java()) ) {
                                alt148=1;
                            }
                            }
                            break;
                    }

                    switch (alt148) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:34: identifierSuffix
                            {
                            pushFollow(FOLLOW_identifierSuffix_in_primary5769);
                            identifierSuffix524=identifierSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_identifierSuffix.add(identifierSuffix524.getTree());

                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: identifierSuffix, Identifier, 116, DOT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 894:52: -> ^( PRIMARY 'this' ( '.' Identifier )* ( identifierSuffix )? )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:55: ^( PRIMARY 'this' ( '.' Identifier )* ( identifierSuffix )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(PRIMARY, "PRIMARY")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_116.nextNode()
                        );

                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:72: ( '.' Identifier )*
                        while ( stream_Identifier.hasNext()||stream_DOT.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_DOT.nextNode()
                            );

                            adaptor.addChild(root_1, 
                            stream_Identifier.nextNode()
                            );

                        }
                        stream_Identifier.reset();
                        stream_DOT.reset();

                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:90: ( identifierSuffix )?
                        if ( stream_identifierSuffix.hasNext() ) {
                            adaptor.addChild(root_1, stream_identifierSuffix.nextTree());

                        }
                        stream_identifierSuffix.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:895:9: SUPER superSuffix
                    {
                    SUPER525=(Token)match(input,SUPER,FOLLOW_SUPER_in_primary5798); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SUPER.add(SUPER525);


                    pushFollow(FOLLOW_superSuffix_in_primary5800);
                    superSuffix526=superSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_superSuffix.add(superSuffix526.getTree());

                    // AST REWRITE
                    // elements: superSuffix, SUPER
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 895:27: -> ^( PRIMARY SUPER superSuffix )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:895:30: ^( PRIMARY SUPER superSuffix )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(PRIMARY, "PRIMARY")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_SUPER.nextNode()
                        );

                        adaptor.addChild(root_1, stream_superSuffix.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:896:9: literal
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_literal_in_primary5820);
                    literal527=literal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, literal527.getTree());

                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:897:9: 'new' creator
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal528=(Token)match(input,106,FOLLOW_106_in_primary5830); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal528_tree = 
                    (CommonTree)adaptor.create(string_literal528)
                    ;
                    adaptor.addChild(root_0, string_literal528_tree);
                    }

                    pushFollow(FOLLOW_creator_in_primary5832);
                    creator529=creator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, creator529.getTree());

                    }
                    break;
                case 6 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:9: Identifier ( '.' Identifier )* ( identifierSuffix )?
                    {
                    Identifier530=(Token)match(input,Identifier,FOLLOW_Identifier_in_primary5842); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_Identifier.add(Identifier530);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:20: ( '.' Identifier )*
                    loop149:
                    do {
                        int alt149=2;
                        int LA149_0 = input.LA(1);

                        if ( (LA149_0==DOT) ) {
                            int LA149_2 = input.LA(2);

                            if ( (LA149_2==Identifier) ) {
                                int LA149_3 = input.LA(3);

                                if ( (synpred242_Java()) ) {
                                    alt149=1;
                                }


                            }


                        }


                        switch (alt149) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:21: '.' Identifier
                    	    {
                    	    char_literal531=(Token)match(input,DOT,FOLLOW_DOT_in_primary5845); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_DOT.add(char_literal531);


                    	    Identifier532=(Token)match(input,Identifier,FOLLOW_Identifier_in_primary5847); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_Identifier.add(Identifier532);


                    	    }
                    	    break;

                    	default :
                    	    break loop149;
                        }
                    } while (true);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:38: ( identifierSuffix )?
                    int alt150=2;
                    switch ( input.LA(1) ) {
                        case 79:
                            {
                            int LA150_1 = input.LA(2);

                            if ( (synpred243_Java()) ) {
                                alt150=1;
                            }
                            }
                            break;
                        case 62:
                            {
                            alt150=1;
                            }
                            break;
                        case DOT:
                            {
                            int LA150_3 = input.LA(2);

                            if ( (synpred243_Java()) ) {
                                alt150=1;
                            }
                            }
                            break;
                    }

                    switch (alt150) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:38: identifierSuffix
                            {
                            pushFollow(FOLLOW_identifierSuffix_in_primary5851);
                            identifierSuffix533=identifierSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_identifierSuffix.add(identifierSuffix533.getTree());

                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: DOT, Identifier, identifierSuffix, Identifier
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 898:56: -> ^( PRIMARY Identifier ( '.' Identifier )* ( identifierSuffix )? )
                    {
                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:59: ^( PRIMARY Identifier ( '.' Identifier )* ( identifierSuffix )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(PRIMARY, "PRIMARY")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_Identifier.nextNode()
                        );

                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:80: ( '.' Identifier )*
                        while ( stream_DOT.hasNext()||stream_Identifier.hasNext() ) {
                            adaptor.addChild(root_1, 
                            stream_DOT.nextNode()
                            );

                            adaptor.addChild(root_1, 
                            stream_Identifier.nextNode()
                            );

                        }
                        stream_DOT.reset();
                        stream_Identifier.reset();

                        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:98: ( identifierSuffix )?
                        if ( stream_identifierSuffix.hasNext() ) {
                            adaptor.addChild(root_1, stream_identifierSuffix.nextTree());

                        }
                        stream_identifierSuffix.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 7 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:899:9: primitiveType ( '[' ']' )* '.' 'class'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_primitiveType_in_primary5880);
                    primitiveType534=primitiveType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primitiveType534.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:899:23: ( '[' ']' )*
                    loop151:
                    do {
                        int alt151=2;
                        int LA151_0 = input.LA(1);

                        if ( (LA151_0==79) ) {
                            alt151=1;
                        }


                        switch (alt151) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:899:24: '[' ']'
                    	    {
                    	    char_literal535=(Token)match(input,79,FOLLOW_79_in_primary5883); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal535_tree = 
                    	    (CommonTree)adaptor.create(char_literal535)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal535_tree);
                    	    }

                    	    char_literal536=(Token)match(input,80,FOLLOW_80_in_primary5885); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal536_tree = 
                    	    (CommonTree)adaptor.create(char_literal536)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal536_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop151;
                        }
                    } while (true);


                    char_literal537=(Token)match(input,DOT,FOLLOW_DOT_in_primary5889); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal537_tree = 
                    (CommonTree)adaptor.create(char_literal537)
                    ;
                    adaptor.addChild(root_0, char_literal537_tree);
                    }

                    string_literal538=(Token)match(input,CLASS,FOLLOW_CLASS_in_primary5891); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal538_tree = 
                    (CommonTree)adaptor.create(string_literal538)
                    ;
                    adaptor.addChild(root_0, string_literal538_tree);
                    }

                    }
                    break;
                case 8 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:900:9: 'void' '.' 'class'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    string_literal539=(Token)match(input,VOID,FOLLOW_VOID_in_primary5901); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal539_tree = 
                    (CommonTree)adaptor.create(string_literal539)
                    ;
                    adaptor.addChild(root_0, string_literal539_tree);
                    }

                    char_literal540=(Token)match(input,DOT,FOLLOW_DOT_in_primary5903); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal540_tree = 
                    (CommonTree)adaptor.create(char_literal540)
                    ;
                    adaptor.addChild(root_0, char_literal540_tree);
                    }

                    string_literal541=(Token)match(input,CLASS,FOLLOW_CLASS_in_primary5905); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal541_tree = 
                    (CommonTree)adaptor.create(string_literal541)
                    ;
                    adaptor.addChild(root_0, string_literal541_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 124, primary_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "primary"


    public static class identifierSuffix_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "identifierSuffix"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:903:1: identifierSuffix : ( ( '[' ']' )+ '.' 'class' | ( '[' expression ']' )+ | arguments | '.' 'class' | '.' explicitGenericInvocation | '.' 'this' | '.' SUPER arguments | '.' 'new' innerCreator );
    public final JavaParser.identifierSuffix_return identifierSuffix() throws RecognitionException {
        JavaParser.identifierSuffix_return retval = new JavaParser.identifierSuffix_return();
        retval.start = input.LT(1);

        int identifierSuffix_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal542=null;
        Token char_literal543=null;
        Token char_literal544=null;
        Token string_literal545=null;
        Token char_literal546=null;
        Token char_literal548=null;
        Token char_literal550=null;
        Token string_literal551=null;
        Token char_literal552=null;
        Token char_literal554=null;
        Token string_literal555=null;
        Token char_literal556=null;
        Token SUPER557=null;
        Token char_literal559=null;
        Token string_literal560=null;
        JavaParser.expression_return expression547 =null;

        JavaParser.arguments_return arguments549 =null;

        JavaParser.explicitGenericInvocation_return explicitGenericInvocation553 =null;

        JavaParser.arguments_return arguments558 =null;

        JavaParser.innerCreator_return innerCreator561 =null;


        CommonTree char_literal542_tree=null;
        CommonTree char_literal543_tree=null;
        CommonTree char_literal544_tree=null;
        CommonTree string_literal545_tree=null;
        CommonTree char_literal546_tree=null;
        CommonTree char_literal548_tree=null;
        CommonTree char_literal550_tree=null;
        CommonTree string_literal551_tree=null;
        CommonTree char_literal552_tree=null;
        CommonTree char_literal554_tree=null;
        CommonTree string_literal555_tree=null;
        CommonTree char_literal556_tree=null;
        CommonTree SUPER557_tree=null;
        CommonTree char_literal559_tree=null;
        CommonTree string_literal560_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 125) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:904:5: ( ( '[' ']' )+ '.' 'class' | ( '[' expression ']' )+ | arguments | '.' 'class' | '.' explicitGenericInvocation | '.' 'this' | '.' SUPER arguments | '.' 'new' innerCreator )
            int alt155=8;
            switch ( input.LA(1) ) {
            case 79:
                {
                int LA155_1 = input.LA(2);

                if ( (LA155_1==80) ) {
                    alt155=1;
                }
                else if ( (LA155_1==CharacterLiteral||LA155_1==DecimalLiteral||LA155_1==FloatingPointLiteral||LA155_1==HexLiteral||LA155_1==Identifier||LA155_1==OctalLiteral||(LA155_1 >= SUPER && LA155_1 <= StringLiteral)||LA155_1==VOID||LA155_1==55||LA155_1==62||(LA155_1 >= 65 && LA155_1 <= 66)||(LA155_1 >= 69 && LA155_1 <= 70)||LA155_1==84||LA155_1==86||LA155_1==89||LA155_1==93||LA155_1==95||LA155_1==98||LA155_1==102||LA155_1==104||(LA155_1 >= 106 && LA155_1 <= 107)||LA155_1==112||LA155_1==116||LA155_1==120||LA155_1==129) ) {
                    alt155=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 155, 1, input);

                    throw nvae;

                }
                }
                break;
            case 62:
                {
                alt155=3;
                }
                break;
            case DOT:
                {
                switch ( input.LA(2) ) {
                case CLASS:
                    {
                    alt155=4;
                    }
                    break;
                case 116:
                    {
                    alt155=6;
                    }
                    break;
                case SUPER:
                    {
                    alt155=7;
                    }
                    break;
                case 106:
                    {
                    alt155=8;
                    }
                    break;
                case LS:
                    {
                    alt155=5;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 155, 3, input);

                    throw nvae;

                }

                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 155, 0, input);

                throw nvae;

            }

            switch (alt155) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:904:9: ( '[' ']' )+ '.' 'class'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:904:9: ( '[' ']' )+
                    int cnt153=0;
                    loop153:
                    do {
                        int alt153=2;
                        int LA153_0 = input.LA(1);

                        if ( (LA153_0==79) ) {
                            alt153=1;
                        }


                        switch (alt153) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:904:10: '[' ']'
                    	    {
                    	    char_literal542=(Token)match(input,79,FOLLOW_79_in_identifierSuffix5925); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal542_tree = 
                    	    (CommonTree)adaptor.create(char_literal542)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal542_tree);
                    	    }

                    	    char_literal543=(Token)match(input,80,FOLLOW_80_in_identifierSuffix5927); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal543_tree = 
                    	    (CommonTree)adaptor.create(char_literal543)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal543_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt153 >= 1 ) break loop153;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(153, input);
                                throw eee;
                        }
                        cnt153++;
                    } while (true);


                    char_literal544=(Token)match(input,DOT,FOLLOW_DOT_in_identifierSuffix5931); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal544_tree = 
                    (CommonTree)adaptor.create(char_literal544)
                    ;
                    adaptor.addChild(root_0, char_literal544_tree);
                    }

                    string_literal545=(Token)match(input,CLASS,FOLLOW_CLASS_in_identifierSuffix5933); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal545_tree = 
                    (CommonTree)adaptor.create(string_literal545)
                    ;
                    adaptor.addChild(root_0, string_literal545_tree);
                    }

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:905:9: ( '[' expression ']' )+
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:905:9: ( '[' expression ']' )+
                    int cnt154=0;
                    loop154:
                    do {
                        int alt154=2;
                        int LA154_0 = input.LA(1);

                        if ( (LA154_0==79) ) {
                            int LA154_2 = input.LA(2);

                            if ( (synpred249_Java()) ) {
                                alt154=1;
                            }


                        }


                        switch (alt154) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:905:10: '[' expression ']'
                    	    {
                    	    char_literal546=(Token)match(input,79,FOLLOW_79_in_identifierSuffix5944); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal546_tree = 
                    	    (CommonTree)adaptor.create(char_literal546)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal546_tree);
                    	    }

                    	    pushFollow(FOLLOW_expression_in_identifierSuffix5946);
                    	    expression547=expression();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression547.getTree());

                    	    char_literal548=(Token)match(input,80,FOLLOW_80_in_identifierSuffix5948); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal548_tree = 
                    	    (CommonTree)adaptor.create(char_literal548)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal548_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt154 >= 1 ) break loop154;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(154, input);
                                throw eee;
                        }
                        cnt154++;
                    } while (true);


                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:906:9: arguments
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_arguments_in_identifierSuffix5961);
                    arguments549=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments549.getTree());

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:907:9: '.' 'class'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal550=(Token)match(input,DOT,FOLLOW_DOT_in_identifierSuffix5971); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal550_tree = 
                    (CommonTree)adaptor.create(char_literal550)
                    ;
                    adaptor.addChild(root_0, char_literal550_tree);
                    }

                    string_literal551=(Token)match(input,CLASS,FOLLOW_CLASS_in_identifierSuffix5973); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal551_tree = 
                    (CommonTree)adaptor.create(string_literal551)
                    ;
                    adaptor.addChild(root_0, string_literal551_tree);
                    }

                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:908:9: '.' explicitGenericInvocation
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal552=(Token)match(input,DOT,FOLLOW_DOT_in_identifierSuffix5983); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal552_tree = 
                    (CommonTree)adaptor.create(char_literal552)
                    ;
                    adaptor.addChild(root_0, char_literal552_tree);
                    }

                    pushFollow(FOLLOW_explicitGenericInvocation_in_identifierSuffix5985);
                    explicitGenericInvocation553=explicitGenericInvocation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, explicitGenericInvocation553.getTree());

                    }
                    break;
                case 6 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:909:9: '.' 'this'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal554=(Token)match(input,DOT,FOLLOW_DOT_in_identifierSuffix5995); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal554_tree = 
                    (CommonTree)adaptor.create(char_literal554)
                    ;
                    adaptor.addChild(root_0, char_literal554_tree);
                    }

                    string_literal555=(Token)match(input,116,FOLLOW_116_in_identifierSuffix5997); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal555_tree = 
                    (CommonTree)adaptor.create(string_literal555)
                    ;
                    adaptor.addChild(root_0, string_literal555_tree);
                    }

                    }
                    break;
                case 7 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:910:9: '.' SUPER arguments
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal556=(Token)match(input,DOT,FOLLOW_DOT_in_identifierSuffix6007); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal556_tree = 
                    (CommonTree)adaptor.create(char_literal556)
                    ;
                    adaptor.addChild(root_0, char_literal556_tree);
                    }

                    SUPER557=(Token)match(input,SUPER,FOLLOW_SUPER_in_identifierSuffix6009); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SUPER557_tree = 
                    (CommonTree)adaptor.create(SUPER557)
                    ;
                    adaptor.addChild(root_0, SUPER557_tree);
                    }

                    pushFollow(FOLLOW_arguments_in_identifierSuffix6011);
                    arguments558=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments558.getTree());

                    }
                    break;
                case 8 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:911:9: '.' 'new' innerCreator
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal559=(Token)match(input,DOT,FOLLOW_DOT_in_identifierSuffix6021); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal559_tree = 
                    (CommonTree)adaptor.create(char_literal559)
                    ;
                    adaptor.addChild(root_0, char_literal559_tree);
                    }

                    string_literal560=(Token)match(input,106,FOLLOW_106_in_identifierSuffix6023); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal560_tree = 
                    (CommonTree)adaptor.create(string_literal560)
                    ;
                    adaptor.addChild(root_0, string_literal560_tree);
                    }

                    pushFollow(FOLLOW_innerCreator_in_identifierSuffix6025);
                    innerCreator561=innerCreator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, innerCreator561.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 125, identifierSuffix_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "identifierSuffix"


    public static class creator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "creator"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:914:1: creator : ( nonWildcardTypeArguments createdName classCreatorRest | createdName ( arrayCreatorRest | classCreatorRest ) );
    public final JavaParser.creator_return creator() throws RecognitionException {
        JavaParser.creator_return retval = new JavaParser.creator_return();
        retval.start = input.LT(1);

        int creator_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments562 =null;

        JavaParser.createdName_return createdName563 =null;

        JavaParser.classCreatorRest_return classCreatorRest564 =null;

        JavaParser.createdName_return createdName565 =null;

        JavaParser.arrayCreatorRest_return arrayCreatorRest566 =null;

        JavaParser.classCreatorRest_return classCreatorRest567 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 126) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:915:5: ( nonWildcardTypeArguments createdName classCreatorRest | createdName ( arrayCreatorRest | classCreatorRest ) )
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==LS) ) {
                alt157=1;
            }
            else if ( (LA157_0==Identifier||LA157_0==84||LA157_0==86||LA157_0==89||LA157_0==93||LA157_0==98||LA157_0==102||LA157_0==104||LA157_0==112) ) {
                alt157=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 157, 0, input);

                throw nvae;

            }
            switch (alt157) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:915:9: nonWildcardTypeArguments createdName classCreatorRest
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_nonWildcardTypeArguments_in_creator6044);
                    nonWildcardTypeArguments562=nonWildcardTypeArguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nonWildcardTypeArguments562.getTree());

                    pushFollow(FOLLOW_createdName_in_creator6046);
                    createdName563=createdName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, createdName563.getTree());

                    pushFollow(FOLLOW_classCreatorRest_in_creator6048);
                    classCreatorRest564=classCreatorRest();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classCreatorRest564.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:916:9: createdName ( arrayCreatorRest | classCreatorRest )
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_createdName_in_creator6058);
                    createdName565=createdName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, createdName565.getTree());

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:916:21: ( arrayCreatorRest | classCreatorRest )
                    int alt156=2;
                    int LA156_0 = input.LA(1);

                    if ( (LA156_0==79) ) {
                        alt156=1;
                    }
                    else if ( (LA156_0==62) ) {
                        alt156=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 156, 0, input);

                        throw nvae;

                    }
                    switch (alt156) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:916:22: arrayCreatorRest
                            {
                            pushFollow(FOLLOW_arrayCreatorRest_in_creator6061);
                            arrayCreatorRest566=arrayCreatorRest();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayCreatorRest566.getTree());

                            }
                            break;
                        case 2 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:916:41: classCreatorRest
                            {
                            pushFollow(FOLLOW_classCreatorRest_in_creator6065);
                            classCreatorRest567=classCreatorRest();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, classCreatorRest567.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 126, creator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "creator"


    public static class createdName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "createdName"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:919:1: createdName : ( classOrInterfaceType | primitiveType );
    public final JavaParser.createdName_return createdName() throws RecognitionException {
        JavaParser.createdName_return retval = new JavaParser.createdName_return();
        retval.start = input.LT(1);

        int createdName_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.classOrInterfaceType_return classOrInterfaceType568 =null;

        JavaParser.primitiveType_return primitiveType569 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 127) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:920:5: ( classOrInterfaceType | primitiveType )
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==Identifier) ) {
                alt158=1;
            }
            else if ( (LA158_0==84||LA158_0==86||LA158_0==89||LA158_0==93||LA158_0==98||LA158_0==102||LA158_0==104||LA158_0==112) ) {
                alt158=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 158, 0, input);

                throw nvae;

            }
            switch (alt158) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:920:9: classOrInterfaceType
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_classOrInterfaceType_in_createdName6085);
                    classOrInterfaceType568=classOrInterfaceType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classOrInterfaceType568.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:921:9: primitiveType
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_primitiveType_in_createdName6095);
                    primitiveType569=primitiveType();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, primitiveType569.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 127, createdName_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "createdName"


    public static class innerCreator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "innerCreator"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:924:1: innerCreator : ( nonWildcardTypeArguments )? Identifier classCreatorRest ;
    public final JavaParser.innerCreator_return innerCreator() throws RecognitionException {
        JavaParser.innerCreator_return retval = new JavaParser.innerCreator_return();
        retval.start = input.LT(1);

        int innerCreator_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier571=null;
        JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments570 =null;

        JavaParser.classCreatorRest_return classCreatorRest572 =null;


        CommonTree Identifier571_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 128) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:925:5: ( ( nonWildcardTypeArguments )? Identifier classCreatorRest )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:925:9: ( nonWildcardTypeArguments )? Identifier classCreatorRest
            {
            root_0 = (CommonTree)adaptor.nil();


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:925:9: ( nonWildcardTypeArguments )?
            int alt159=2;
            int LA159_0 = input.LA(1);

            if ( (LA159_0==LS) ) {
                alt159=1;
            }
            switch (alt159) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:925:9: nonWildcardTypeArguments
                    {
                    pushFollow(FOLLOW_nonWildcardTypeArguments_in_innerCreator6118);
                    nonWildcardTypeArguments570=nonWildcardTypeArguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, nonWildcardTypeArguments570.getTree());

                    }
                    break;

            }


            Identifier571=(Token)match(input,Identifier,FOLLOW_Identifier_in_innerCreator6121); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier571_tree = 
            (CommonTree)adaptor.create(Identifier571)
            ;
            adaptor.addChild(root_0, Identifier571_tree);
            }

            pushFollow(FOLLOW_classCreatorRest_in_innerCreator6123);
            classCreatorRest572=classCreatorRest();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, classCreatorRest572.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 128, innerCreator_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "innerCreator"


    public static class arrayCreatorRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arrayCreatorRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:928:1: arrayCreatorRest : '[' ( ']' ( '[' ']' )* arrayInitializer | expression ']' ( '[' expression ']' )* ( '[' ']' )* ) ;
    public final JavaParser.arrayCreatorRest_return arrayCreatorRest() throws RecognitionException {
        JavaParser.arrayCreatorRest_return retval = new JavaParser.arrayCreatorRest_return();
        retval.start = input.LT(1);

        int arrayCreatorRest_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal573=null;
        Token char_literal574=null;
        Token char_literal575=null;
        Token char_literal576=null;
        Token char_literal579=null;
        Token char_literal580=null;
        Token char_literal582=null;
        Token char_literal583=null;
        Token char_literal584=null;
        JavaParser.arrayInitializer_return arrayInitializer577 =null;

        JavaParser.expression_return expression578 =null;

        JavaParser.expression_return expression581 =null;


        CommonTree char_literal573_tree=null;
        CommonTree char_literal574_tree=null;
        CommonTree char_literal575_tree=null;
        CommonTree char_literal576_tree=null;
        CommonTree char_literal579_tree=null;
        CommonTree char_literal580_tree=null;
        CommonTree char_literal582_tree=null;
        CommonTree char_literal583_tree=null;
        CommonTree char_literal584_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 129) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:929:5: ( '[' ( ']' ( '[' ']' )* arrayInitializer | expression ']' ( '[' expression ']' )* ( '[' ']' )* ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:929:9: '[' ( ']' ( '[' ']' )* arrayInitializer | expression ']' ( '[' expression ']' )* ( '[' ']' )* )
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal573=(Token)match(input,79,FOLLOW_79_in_arrayCreatorRest6142); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal573_tree = 
            (CommonTree)adaptor.create(char_literal573)
            ;
            adaptor.addChild(root_0, char_literal573_tree);
            }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:930:9: ( ']' ( '[' ']' )* arrayInitializer | expression ']' ( '[' expression ']' )* ( '[' ']' )* )
            int alt163=2;
            int LA163_0 = input.LA(1);

            if ( (LA163_0==80) ) {
                alt163=1;
            }
            else if ( (LA163_0==CharacterLiteral||LA163_0==DecimalLiteral||LA163_0==FloatingPointLiteral||LA163_0==HexLiteral||LA163_0==Identifier||LA163_0==OctalLiteral||(LA163_0 >= SUPER && LA163_0 <= StringLiteral)||LA163_0==VOID||LA163_0==55||LA163_0==62||(LA163_0 >= 65 && LA163_0 <= 66)||(LA163_0 >= 69 && LA163_0 <= 70)||LA163_0==84||LA163_0==86||LA163_0==89||LA163_0==93||LA163_0==95||LA163_0==98||LA163_0==102||LA163_0==104||(LA163_0 >= 106 && LA163_0 <= 107)||LA163_0==112||LA163_0==116||LA163_0==120||LA163_0==129) ) {
                alt163=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 163, 0, input);

                throw nvae;

            }
            switch (alt163) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:930:13: ']' ( '[' ']' )* arrayInitializer
                    {
                    char_literal574=(Token)match(input,80,FOLLOW_80_in_arrayCreatorRest6156); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal574_tree = 
                    (CommonTree)adaptor.create(char_literal574)
                    ;
                    adaptor.addChild(root_0, char_literal574_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:930:17: ( '[' ']' )*
                    loop160:
                    do {
                        int alt160=2;
                        int LA160_0 = input.LA(1);

                        if ( (LA160_0==79) ) {
                            alt160=1;
                        }


                        switch (alt160) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:930:18: '[' ']'
                    	    {
                    	    char_literal575=(Token)match(input,79,FOLLOW_79_in_arrayCreatorRest6159); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal575_tree = 
                    	    (CommonTree)adaptor.create(char_literal575)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal575_tree);
                    	    }

                    	    char_literal576=(Token)match(input,80,FOLLOW_80_in_arrayCreatorRest6161); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal576_tree = 
                    	    (CommonTree)adaptor.create(char_literal576)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal576_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop160;
                        }
                    } while (true);


                    pushFollow(FOLLOW_arrayInitializer_in_arrayCreatorRest6165);
                    arrayInitializer577=arrayInitializer();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayInitializer577.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:931:13: expression ']' ( '[' expression ']' )* ( '[' ']' )*
                    {
                    pushFollow(FOLLOW_expression_in_arrayCreatorRest6179);
                    expression578=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression578.getTree());

                    char_literal579=(Token)match(input,80,FOLLOW_80_in_arrayCreatorRest6181); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal579_tree = 
                    (CommonTree)adaptor.create(char_literal579)
                    ;
                    adaptor.addChild(root_0, char_literal579_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:931:28: ( '[' expression ']' )*
                    loop161:
                    do {
                        int alt161=2;
                        int LA161_0 = input.LA(1);

                        if ( (LA161_0==79) ) {
                            int LA161_1 = input.LA(2);

                            if ( (synpred262_Java()) ) {
                                alt161=1;
                            }


                        }


                        switch (alt161) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:931:29: '[' expression ']'
                    	    {
                    	    char_literal580=(Token)match(input,79,FOLLOW_79_in_arrayCreatorRest6184); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal580_tree = 
                    	    (CommonTree)adaptor.create(char_literal580)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal580_tree);
                    	    }

                    	    pushFollow(FOLLOW_expression_in_arrayCreatorRest6186);
                    	    expression581=expression();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression581.getTree());

                    	    char_literal582=(Token)match(input,80,FOLLOW_80_in_arrayCreatorRest6188); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal582_tree = 
                    	    (CommonTree)adaptor.create(char_literal582)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal582_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop161;
                        }
                    } while (true);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:931:50: ( '[' ']' )*
                    loop162:
                    do {
                        int alt162=2;
                        int LA162_0 = input.LA(1);

                        if ( (LA162_0==79) ) {
                            int LA162_2 = input.LA(2);

                            if ( (LA162_2==80) ) {
                                alt162=1;
                            }


                        }


                        switch (alt162) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:931:51: '[' ']'
                    	    {
                    	    char_literal583=(Token)match(input,79,FOLLOW_79_in_arrayCreatorRest6193); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal583_tree = 
                    	    (CommonTree)adaptor.create(char_literal583)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal583_tree);
                    	    }

                    	    char_literal584=(Token)match(input,80,FOLLOW_80_in_arrayCreatorRest6195); if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) {
                    	    char_literal584_tree = 
                    	    (CommonTree)adaptor.create(char_literal584)
                    	    ;
                    	    adaptor.addChild(root_0, char_literal584_tree);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop162;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 129, arrayCreatorRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "arrayCreatorRest"


    public static class classCreatorRest_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "classCreatorRest"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:935:1: classCreatorRest : arguments ( classBody )? ;
    public final JavaParser.classCreatorRest_return classCreatorRest() throws RecognitionException {
        JavaParser.classCreatorRest_return retval = new JavaParser.classCreatorRest_return();
        retval.start = input.LT(1);

        int classCreatorRest_StartIndex = input.index();

        CommonTree root_0 = null;

        JavaParser.arguments_return arguments585 =null;

        JavaParser.classBody_return classBody586 =null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 130) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:936:5: ( arguments ( classBody )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:936:9: arguments ( classBody )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_arguments_in_classCreatorRest6226);
            arguments585=arguments();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments585.getTree());

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:936:19: ( classBody )?
            int alt164=2;
            int LA164_0 = input.LA(1);

            if ( (LA164_0==124) ) {
                alt164=1;
            }
            switch (alt164) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:936:19: classBody
                    {
                    pushFollow(FOLLOW_classBody_in_classCreatorRest6228);
                    classBody586=classBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, classBody586.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 130, classCreatorRest_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "classCreatorRest"


    public static class explicitGenericInvocation_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "explicitGenericInvocation"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:939:1: explicitGenericInvocation : nonWildcardTypeArguments Identifier arguments ;
    public final JavaParser.explicitGenericInvocation_return explicitGenericInvocation() throws RecognitionException {
        JavaParser.explicitGenericInvocation_return retval = new JavaParser.explicitGenericInvocation_return();
        retval.start = input.LT(1);

        int explicitGenericInvocation_StartIndex = input.index();

        CommonTree root_0 = null;

        Token Identifier588=null;
        JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments587 =null;

        JavaParser.arguments_return arguments589 =null;


        CommonTree Identifier588_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 131) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:940:5: ( nonWildcardTypeArguments Identifier arguments )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:940:9: nonWildcardTypeArguments Identifier arguments
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_nonWildcardTypeArguments_in_explicitGenericInvocation6252);
            nonWildcardTypeArguments587=nonWildcardTypeArguments();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, nonWildcardTypeArguments587.getTree());

            Identifier588=(Token)match(input,Identifier,FOLLOW_Identifier_in_explicitGenericInvocation6254); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            Identifier588_tree = 
            (CommonTree)adaptor.create(Identifier588)
            ;
            adaptor.addChild(root_0, Identifier588_tree);
            }

            pushFollow(FOLLOW_arguments_in_explicitGenericInvocation6256);
            arguments589=arguments();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments589.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 131, explicitGenericInvocation_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "explicitGenericInvocation"


    public static class nonWildcardTypeArguments_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "nonWildcardTypeArguments"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:943:1: nonWildcardTypeArguments : '<' typeList '>' ;
    public final JavaParser.nonWildcardTypeArguments_return nonWildcardTypeArguments() throws RecognitionException {
        JavaParser.nonWildcardTypeArguments_return retval = new JavaParser.nonWildcardTypeArguments_return();
        retval.start = input.LT(1);

        int nonWildcardTypeArguments_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal590=null;
        Token char_literal592=null;
        JavaParser.typeList_return typeList591 =null;


        CommonTree char_literal590_tree=null;
        CommonTree char_literal592_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 132) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:944:5: ( '<' typeList '>' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:944:9: '<' typeList '>'
            {
            root_0 = (CommonTree)adaptor.nil();


            char_literal590=(Token)match(input,LS,FOLLOW_LS_in_nonWildcardTypeArguments6279); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal590_tree = 
            (CommonTree)adaptor.create(char_literal590)
            ;
            adaptor.addChild(root_0, char_literal590_tree);
            }

            pushFollow(FOLLOW_typeList_in_nonWildcardTypeArguments6281);
            typeList591=typeList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, typeList591.getTree());

            char_literal592=(Token)match(input,GT,FOLLOW_GT_in_nonWildcardTypeArguments6283); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal592_tree = 
            (CommonTree)adaptor.create(char_literal592)
            ;
            adaptor.addChild(root_0, char_literal592_tree);
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 132, nonWildcardTypeArguments_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "nonWildcardTypeArguments"


    public static class selector_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "selector"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:947:1: selector : ( '.' Identifier ( arguments )? | '.' 'this' | '.' SUPER superSuffix | '.' 'new' innerCreator | '[' expression ']' );
    public final JavaParser.selector_return selector() throws RecognitionException {
        JavaParser.selector_return retval = new JavaParser.selector_return();
        retval.start = input.LT(1);

        int selector_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal593=null;
        Token Identifier594=null;
        Token char_literal596=null;
        Token string_literal597=null;
        Token char_literal598=null;
        Token SUPER599=null;
        Token char_literal601=null;
        Token string_literal602=null;
        Token char_literal604=null;
        Token char_literal606=null;
        JavaParser.arguments_return arguments595 =null;

        JavaParser.superSuffix_return superSuffix600 =null;

        JavaParser.innerCreator_return innerCreator603 =null;

        JavaParser.expression_return expression605 =null;


        CommonTree char_literal593_tree=null;
        CommonTree Identifier594_tree=null;
        CommonTree char_literal596_tree=null;
        CommonTree string_literal597_tree=null;
        CommonTree char_literal598_tree=null;
        CommonTree SUPER599_tree=null;
        CommonTree char_literal601_tree=null;
        CommonTree string_literal602_tree=null;
        CommonTree char_literal604_tree=null;
        CommonTree char_literal606_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 133) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:948:5: ( '.' Identifier ( arguments )? | '.' 'this' | '.' SUPER superSuffix | '.' 'new' innerCreator | '[' expression ']' )
            int alt166=5;
            int LA166_0 = input.LA(1);

            if ( (LA166_0==DOT) ) {
                switch ( input.LA(2) ) {
                case Identifier:
                    {
                    alt166=1;
                    }
                    break;
                case 116:
                    {
                    alt166=2;
                    }
                    break;
                case SUPER:
                    {
                    alt166=3;
                    }
                    break;
                case 106:
                    {
                    alt166=4;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 166, 1, input);

                    throw nvae;

                }

            }
            else if ( (LA166_0==79) ) {
                alt166=5;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 166, 0, input);

                throw nvae;

            }
            switch (alt166) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:948:9: '.' Identifier ( arguments )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal593=(Token)match(input,DOT,FOLLOW_DOT_in_selector6306); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal593_tree = 
                    (CommonTree)adaptor.create(char_literal593)
                    ;
                    adaptor.addChild(root_0, char_literal593_tree);
                    }

                    Identifier594=(Token)match(input,Identifier,FOLLOW_Identifier_in_selector6308); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Identifier594_tree = 
                    (CommonTree)adaptor.create(Identifier594)
                    ;
                    adaptor.addChild(root_0, Identifier594_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:948:24: ( arguments )?
                    int alt165=2;
                    int LA165_0 = input.LA(1);

                    if ( (LA165_0==62) ) {
                        alt165=1;
                    }
                    switch (alt165) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:948:24: arguments
                            {
                            pushFollow(FOLLOW_arguments_in_selector6310);
                            arguments595=arguments();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments595.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:949:9: '.' 'this'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal596=(Token)match(input,DOT,FOLLOW_DOT_in_selector6321); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal596_tree = 
                    (CommonTree)adaptor.create(char_literal596)
                    ;
                    adaptor.addChild(root_0, char_literal596_tree);
                    }

                    string_literal597=(Token)match(input,116,FOLLOW_116_in_selector6323); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal597_tree = 
                    (CommonTree)adaptor.create(string_literal597)
                    ;
                    adaptor.addChild(root_0, string_literal597_tree);
                    }

                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:950:9: '.' SUPER superSuffix
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal598=(Token)match(input,DOT,FOLLOW_DOT_in_selector6333); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal598_tree = 
                    (CommonTree)adaptor.create(char_literal598)
                    ;
                    adaptor.addChild(root_0, char_literal598_tree);
                    }

                    SUPER599=(Token)match(input,SUPER,FOLLOW_SUPER_in_selector6335); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SUPER599_tree = 
                    (CommonTree)adaptor.create(SUPER599)
                    ;
                    adaptor.addChild(root_0, SUPER599_tree);
                    }

                    pushFollow(FOLLOW_superSuffix_in_selector6337);
                    superSuffix600=superSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, superSuffix600.getTree());

                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:951:9: '.' 'new' innerCreator
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal601=(Token)match(input,DOT,FOLLOW_DOT_in_selector6347); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal601_tree = 
                    (CommonTree)adaptor.create(char_literal601)
                    ;
                    adaptor.addChild(root_0, char_literal601_tree);
                    }

                    string_literal602=(Token)match(input,106,FOLLOW_106_in_selector6349); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal602_tree = 
                    (CommonTree)adaptor.create(string_literal602)
                    ;
                    adaptor.addChild(root_0, string_literal602_tree);
                    }

                    pushFollow(FOLLOW_innerCreator_in_selector6351);
                    innerCreator603=innerCreator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, innerCreator603.getTree());

                    }
                    break;
                case 5 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:952:9: '[' expression ']'
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal604=(Token)match(input,79,FOLLOW_79_in_selector6361); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal604_tree = 
                    (CommonTree)adaptor.create(char_literal604)
                    ;
                    adaptor.addChild(root_0, char_literal604_tree);
                    }

                    pushFollow(FOLLOW_expression_in_selector6363);
                    expression605=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expression605.getTree());

                    char_literal606=(Token)match(input,80,FOLLOW_80_in_selector6365); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal606_tree = 
                    (CommonTree)adaptor.create(char_literal606)
                    ;
                    adaptor.addChild(root_0, char_literal606_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 133, selector_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "selector"


    public static class superSuffix_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "superSuffix"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:955:1: superSuffix : ( arguments | '.' Identifier ( arguments )? );
    public final JavaParser.superSuffix_return superSuffix() throws RecognitionException {
        JavaParser.superSuffix_return retval = new JavaParser.superSuffix_return();
        retval.start = input.LT(1);

        int superSuffix_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal608=null;
        Token Identifier609=null;
        JavaParser.arguments_return arguments607 =null;

        JavaParser.arguments_return arguments610 =null;


        CommonTree char_literal608_tree=null;
        CommonTree Identifier609_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 134) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:956:5: ( arguments | '.' Identifier ( arguments )? )
            int alt168=2;
            int LA168_0 = input.LA(1);

            if ( (LA168_0==62) ) {
                alt168=1;
            }
            else if ( (LA168_0==DOT) ) {
                alt168=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 168, 0, input);

                throw nvae;

            }
            switch (alt168) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:956:9: arguments
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    pushFollow(FOLLOW_arguments_in_superSuffix6388);
                    arguments607=arguments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments607.getTree());

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:957:9: '.' Identifier ( arguments )?
                    {
                    root_0 = (CommonTree)adaptor.nil();


                    char_literal608=(Token)match(input,DOT,FOLLOW_DOT_in_superSuffix6398); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal608_tree = 
                    (CommonTree)adaptor.create(char_literal608)
                    ;
                    adaptor.addChild(root_0, char_literal608_tree);
                    }

                    Identifier609=(Token)match(input,Identifier,FOLLOW_Identifier_in_superSuffix6400); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    Identifier609_tree = 
                    (CommonTree)adaptor.create(Identifier609)
                    ;
                    adaptor.addChild(root_0, Identifier609_tree);
                    }

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:957:24: ( arguments )?
                    int alt167=2;
                    int LA167_0 = input.LA(1);

                    if ( (LA167_0==62) ) {
                        alt167=1;
                    }
                    switch (alt167) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:957:24: arguments
                            {
                            pushFollow(FOLLOW_arguments_in_superSuffix6402);
                            arguments610=arguments();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, arguments610.getTree());

                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 134, superSuffix_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "superSuffix"


    public static class arguments_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arguments"
    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:960:1: arguments : '(' ( expressionList )? ')' -> ^( ARGS ( expressionList )? ) ;
    public final JavaParser.arguments_return arguments() throws RecognitionException {
        JavaParser.arguments_return retval = new JavaParser.arguments_return();
        retval.start = input.LT(1);

        int arguments_StartIndex = input.index();

        CommonTree root_0 = null;

        Token char_literal611=null;
        Token char_literal613=null;
        JavaParser.expressionList_return expressionList612 =null;


        CommonTree char_literal611_tree=null;
        CommonTree char_literal613_tree=null;
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleSubtreeStream stream_expressionList=new RewriteRuleSubtreeStream(adaptor,"rule expressionList");
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 135) ) { return retval; }

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:961:5: ( '(' ( expressionList )? ')' -> ^( ARGS ( expressionList )? ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:961:9: '(' ( expressionList )? ')'
            {
            char_literal611=(Token)match(input,62,FOLLOW_62_in_arguments6422); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_62.add(char_literal611);


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:961:13: ( expressionList )?
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( (LA169_0==CharacterLiteral||LA169_0==DecimalLiteral||LA169_0==FloatingPointLiteral||LA169_0==HexLiteral||LA169_0==Identifier||LA169_0==OctalLiteral||(LA169_0 >= SUPER && LA169_0 <= StringLiteral)||LA169_0==VOID||LA169_0==55||LA169_0==62||(LA169_0 >= 65 && LA169_0 <= 66)||(LA169_0 >= 69 && LA169_0 <= 70)||LA169_0==84||LA169_0==86||LA169_0==89||LA169_0==93||LA169_0==95||LA169_0==98||LA169_0==102||LA169_0==104||(LA169_0 >= 106 && LA169_0 <= 107)||LA169_0==112||LA169_0==116||LA169_0==120||LA169_0==129) ) {
                alt169=1;
            }
            switch (alt169) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:961:13: expressionList
                    {
                    pushFollow(FOLLOW_expressionList_in_arguments6424);
                    expressionList612=expressionList();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expressionList.add(expressionList612.getTree());

                    }
                    break;

            }


            char_literal613=(Token)match(input,63,FOLLOW_63_in_arguments6427); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_63.add(char_literal613);


            // AST REWRITE
            // elements: expressionList
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 961:33: -> ^( ARGS ( expressionList )? )
            {
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:961:36: ^( ARGS ( expressionList )? )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(ARGS, "ARGS")
                , root_1);

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:961:43: ( expressionList )?
                if ( stream_expressionList.hasNext() ) {
                    adaptor.addChild(root_1, stream_expressionList.nextTree());

                }
                stream_expressionList.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 135, arguments_StartIndex); }

        }
        return retval;
    }
    // $ANTLR end "arguments"

    // $ANTLR start synpred5_Java
    public final void synpred5_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:232:9: ( annotations ( packageDeclaration ( importDeclaration )* ( typeDeclaration )* | classOrInterfaceDeclaration ( typeDeclaration )* ) )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:232:9: annotations ( packageDeclaration ( importDeclaration )* ( typeDeclaration )* | classOrInterfaceDeclaration ( typeDeclaration )* )
        {
        pushFollow(FOLLOW_annotations_in_synpred5_Java282);
        annotations();

        state._fsp--;
        if (state.failed) return ;

        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:9: ( packageDeclaration ( importDeclaration )* ( typeDeclaration )* | classOrInterfaceDeclaration ( typeDeclaration )* )
        int alt175=2;
        int LA175_0 = input.LA(1);

        if ( (LA175_0==PACKAGE) ) {
            alt175=1;
        }
        else if ( (LA175_0==CLASS||LA175_0==ENUM||LA175_0==STATIC||LA175_0==78||LA175_0==83||LA175_0==96||LA175_0==103||(LA175_0 >= 108 && LA175_0 <= 110)||LA175_0==113) ) {
            alt175=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 175, 0, input);

            throw nvae;

        }
        switch (alt175) {
            case 1 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:13: packageDeclaration ( importDeclaration )* ( typeDeclaration )*
                {
                pushFollow(FOLLOW_packageDeclaration_in_synpred5_Java296);
                packageDeclaration();

                state._fsp--;
                if (state.failed) return ;

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:32: ( importDeclaration )*
                loop172:
                do {
                    int alt172=2;
                    int LA172_0 = input.LA(1);

                    if ( (LA172_0==IMPORT) ) {
                        alt172=1;
                    }


                    switch (alt172) {
                	case 1 :
                	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:32: importDeclaration
                	    {
                	    pushFollow(FOLLOW_importDeclaration_in_synpred5_Java298);
                	    importDeclaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop172;
                    }
                } while (true);


                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:51: ( typeDeclaration )*
                loop173:
                do {
                    int alt173=2;
                    int LA173_0 = input.LA(1);

                    if ( (LA173_0==CLASS||LA173_0==ENUM||LA173_0==SEMI||LA173_0==STATIC||LA173_0==78||LA173_0==83||LA173_0==96||LA173_0==103||(LA173_0 >= 108 && LA173_0 <= 110)||LA173_0==113) ) {
                        alt173=1;
                    }


                    switch (alt173) {
                	case 1 :
                	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:233:51: typeDeclaration
                	    {
                	    pushFollow(FOLLOW_typeDeclaration_in_synpred5_Java301);
                	    typeDeclaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop173;
                    }
                } while (true);


                }
                break;
            case 2 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:234:13: classOrInterfaceDeclaration ( typeDeclaration )*
                {
                pushFollow(FOLLOW_classOrInterfaceDeclaration_in_synpred5_Java316);
                classOrInterfaceDeclaration();

                state._fsp--;
                if (state.failed) return ;

                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:234:41: ( typeDeclaration )*
                loop174:
                do {
                    int alt174=2;
                    int LA174_0 = input.LA(1);

                    if ( (LA174_0==CLASS||LA174_0==ENUM||LA174_0==SEMI||LA174_0==STATIC||LA174_0==78||LA174_0==83||LA174_0==96||LA174_0==103||(LA174_0 >= 108 && LA174_0 <= 110)||LA174_0==113) ) {
                        alt174=1;
                    }


                    switch (alt174) {
                	case 1 :
                	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:234:41: typeDeclaration
                	    {
                	    pushFollow(FOLLOW_typeDeclaration_in_synpred5_Java318);
                	    typeDeclaration();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }
                	    break;

                	default :
                	    break loop174;
                    }
                } while (true);


                }
                break;

        }


        }

    }
    // $ANTLR end synpred5_Java

    // $ANTLR start synpred53_Java
    public final void synpred53_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:357:9: ( type methodDeclaration )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:357:9: type methodDeclaration
        {
        pushFollow(FOLLOW_type_in_synpred53_Java1424);
        type();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_methodDeclaration_in_synpred53_Java1426);
        methodDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred53_Java

    // $ANTLR start synpred113_Java
    public final void synpred113_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:549:13: ( explicitConstructorInvocation )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:549:13: explicitConstructorInvocation
        {
        pushFollow(FOLLOW_explicitConstructorInvocation_in_synpred113_Java3039);
        explicitConstructorInvocation();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred113_Java

    // $ANTLR start synpred117_Java
    public final void synpred117_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:553:9: ( ( nonWildcardTypeArguments )? ( 'this' | SUPER ) arguments ';' )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:553:9: ( nonWildcardTypeArguments )? ( 'this' | SUPER ) arguments ';'
        {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:553:9: ( nonWildcardTypeArguments )?
        int alt183=2;
        int LA183_0 = input.LA(1);

        if ( (LA183_0==LS) ) {
            alt183=1;
        }
        switch (alt183) {
            case 1 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:553:9: nonWildcardTypeArguments
                {
                pushFollow(FOLLOW_nonWildcardTypeArguments_in_synpred117_Java3064);
                nonWildcardTypeArguments();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }


        if ( input.LA(1)==SUPER||input.LA(1)==116 ) {
            input.consume();
            state.errorRecovery=false;
            state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        pushFollow(FOLLOW_arguments_in_synpred117_Java3075);
        arguments();

        state._fsp--;
        if (state.failed) return ;

        match(input,SEMI,FOLLOW_SEMI_in_synpred117_Java3077); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred117_Java

    // $ANTLR start synpred128_Java
    public final void synpred128_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:585:9: ( annotation )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:585:9: annotation
        {
        pushFollow(FOLLOW_annotation_in_synpred128_Java3308);
        annotation();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred128_Java

    // $ANTLR start synpred151_Java
    public final void synpred151_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:658:9: ( localVariableDeclarationStatement )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:658:9: localVariableDeclarationStatement
        {
        pushFollow(FOLLOW_localVariableDeclarationStatement_in_synpred151_Java3862);
        localVariableDeclarationStatement();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred151_Java

    // $ANTLR start synpred152_Java
    public final void synpred152_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:659:9: ( classOrInterfaceDeclaration )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:659:9: classOrInterfaceDeclaration
        {
        pushFollow(FOLLOW_classOrInterfaceDeclaration_in_synpred152_Java3872);
        classOrInterfaceDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred152_Java

    // $ANTLR start synpred157_Java
    public final void synpred157_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:678:54: ( 'else' statement )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:678:54: 'else' statement
        {
        match(input,94,FOLLOW_94_in_synpred157_Java4030); if (state.failed) return ;

        pushFollow(FOLLOW_statement_in_synpred157_Java4032);
        statement();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred157_Java

    // $ANTLR start synpred162_Java
    public final void synpred162_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:683:11: ( catches 'finally' block )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:683:11: catches 'finally' block
        {
        pushFollow(FOLLOW_catches_in_synpred162_Java4108);
        catches();

        state._fsp--;
        if (state.failed) return ;

        match(input,97,FOLLOW_97_in_synpred162_Java4110); if (state.failed) return ;

        pushFollow(FOLLOW_block_in_synpred162_Java4112);
        block();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred162_Java

    // $ANTLR start synpred163_Java
    public final void synpred163_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:684:11: ( catches )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:684:11: catches
        {
        pushFollow(FOLLOW_catches_in_synpred163_Java4124);
        catches();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred163_Java

    // $ANTLR start synpred178_Java
    public final void synpred178_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:719:9: ( switchLabel )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:719:9: switchLabel
        {
        pushFollow(FOLLOW_switchLabel_in_synpred178_Java4428);
        switchLabel();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred178_Java

    // $ANTLR start synpred180_Java
    public final void synpred180_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:723:9: ( 'case' constantExpression ':' )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:723:9: 'case' constantExpression ':'
        {
        match(input,87,FOLLOW_87_in_synpred180_Java4455); if (state.failed) return ;

        pushFollow(FOLLOW_constantExpression_in_synpred180_Java4457);
        constantExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,75,FOLLOW_75_in_synpred180_Java4459); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred180_Java

    // $ANTLR start synpred181_Java
    public final void synpred181_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:724:9: ( 'case' enumConstantName ':' )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:724:9: 'case' enumConstantName ':'
        {
        match(input,87,FOLLOW_87_in_synpred181_Java4469); if (state.failed) return ;

        pushFollow(FOLLOW_enumConstantName_in_synpred181_Java4471);
        enumConstantName();

        state._fsp--;
        if (state.failed) return ;

        match(input,75,FOLLOW_75_in_synpred181_Java4473); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred181_Java

    // $ANTLR start synpred182_Java
    public final void synpred182_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:730:9: ( enhancedForControl )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:730:9: enhancedForControl
        {
        pushFollow(FOLLOW_enhancedForControl_in_synpred182_Java4516);
        enhancedForControl();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred182_Java

    // $ANTLR start synpred186_Java
    public final void synpred186_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:735:9: ( localVariableDeclaration )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:735:9: localVariableDeclaration
        {
        pushFollow(FOLLOW_localVariableDeclaration_in_synpred186_Java4556);
        localVariableDeclaration();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred186_Java

    // $ANTLR start synpred188_Java
    public final void synpred188_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:766:32: ( assignmentOperator expression )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:766:32: assignmentOperator expression
        {
        pushFollow(FOLLOW_assignmentOperator_in_synpred188_Java4739);
        assignmentOperator();

        state._fsp--;
        if (state.failed) return ;

        pushFollow(FOLLOW_expression_in_synpred188_Java4741);
        expression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred188_Java

    // $ANTLR start synpred198_Java
    public final void synpred198_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:779:9: ( '<' '<' '=' )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:779:10: '<' '<' '='
        {
        match(input,LS,FOLLOW_LS_in_synpred198_Java4857); if (state.failed) return ;

        match(input,LS,FOLLOW_LS_in_synpred198_Java4859); if (state.failed) return ;

        match(input,EQ,FOLLOW_EQ_in_synpred198_Java4861); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred198_Java

    // $ANTLR start synpred199_Java
    public final void synpred199_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:784:9: ( '>' '>' '>' '=' )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:784:10: '>' '>' '>' '='
        {
        match(input,GT,FOLLOW_GT_in_synpred199_Java4897); if (state.failed) return ;

        match(input,GT,FOLLOW_GT_in_synpred199_Java4899); if (state.failed) return ;

        match(input,GT,FOLLOW_GT_in_synpred199_Java4901); if (state.failed) return ;

        match(input,EQ,FOLLOW_EQ_in_synpred199_Java4903); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred199_Java

    // $ANTLR start synpred200_Java
    public final void synpred200_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:791:9: ( '>' '>' '=' )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:791:10: '>' '>' '='
        {
        match(input,GT,FOLLOW_GT_in_synpred200_Java4942); if (state.failed) return ;

        match(input,GT,FOLLOW_GT_in_synpred200_Java4944); if (state.failed) return ;

        match(input,EQ,FOLLOW_EQ_in_synpred200_Java4946); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred200_Java

    // $ANTLR start synpred211_Java
    public final void synpred211_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:835:9: ( LS EQ )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:835:10: LS EQ
        {
        match(input,LS,FOLLOW_LS_in_synpred211_Java5254); if (state.failed) return ;

        match(input,EQ,FOLLOW_EQ_in_synpred211_Java5256); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred211_Java

    // $ANTLR start synpred212_Java
    public final void synpred212_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:838:9: ( GT EQ )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:838:10: GT EQ
        {
        match(input,GT,FOLLOW_GT_in_synpred212_Java5288); if (state.failed) return ;

        match(input,EQ,FOLLOW_EQ_in_synpred212_Java5290); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred212_Java

    // $ANTLR start synpred215_Java
    public final void synpred215_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:850:9: ( '<' '<' )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:850:10: '<' '<'
        {
        match(input,LS,FOLLOW_LS_in_synpred215_Java5381); if (state.failed) return ;

        match(input,LS,FOLLOW_LS_in_synpred215_Java5383); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred215_Java

    // $ANTLR start synpred216_Java
    public final void synpred216_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:853:9: ( '>' '>' '>' )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:853:10: '>' '>' '>'
        {
        match(input,GT,FOLLOW_GT_in_synpred216_Java5415); if (state.failed) return ;

        match(input,GT,FOLLOW_GT_in_synpred216_Java5417); if (state.failed) return ;

        match(input,GT,FOLLOW_GT_in_synpred216_Java5419); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred216_Java

    // $ANTLR start synpred217_Java
    public final void synpred217_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:858:9: ( '>' '>' )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:858:10: '>' '>'
        {
        match(input,GT,FOLLOW_GT_in_synpred217_Java5455); if (state.failed) return ;

        match(input,GT,FOLLOW_GT_in_synpred217_Java5457); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred217_Java

    // $ANTLR start synpred229_Java
    public final void synpred229_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:883:9: ( castExpression )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:883:9: castExpression
        {
        pushFollow(FOLLOW_castExpression_in_synpred229_Java5666);
        castExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred229_Java

    // $ANTLR start synpred233_Java
    public final void synpred233_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:888:8: ( '(' primitiveType ')' unaryExpression )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:888:8: '(' primitiveType ')' unaryExpression
        {
        match(input,62,FOLLOW_62_in_synpred233_Java5704); if (state.failed) return ;

        pushFollow(FOLLOW_primitiveType_in_synpred233_Java5706);
        primitiveType();

        state._fsp--;
        if (state.failed) return ;

        match(input,63,FOLLOW_63_in_synpred233_Java5708); if (state.failed) return ;

        pushFollow(FOLLOW_unaryExpression_in_synpred233_Java5710);
        unaryExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred233_Java

    // $ANTLR start synpred234_Java
    public final void synpred234_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:889:13: ( type )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:889:13: type
        {
        pushFollow(FOLLOW_type_in_synpred234_Java5722);
        type();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred234_Java

    // $ANTLR start synpred236_Java
    public final void synpred236_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:17: ( '.' Identifier )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:17: '.' Identifier
        {
        match(input,DOT,FOLLOW_DOT_in_synpred236_Java5763); if (state.failed) return ;

        match(input,Identifier,FOLLOW_Identifier_in_synpred236_Java5765); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred236_Java

    // $ANTLR start synpred237_Java
    public final void synpred237_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:34: ( identifierSuffix )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:894:34: identifierSuffix
        {
        pushFollow(FOLLOW_identifierSuffix_in_synpred237_Java5769);
        identifierSuffix();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred237_Java

    // $ANTLR start synpred242_Java
    public final void synpred242_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:21: ( '.' Identifier )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:21: '.' Identifier
        {
        match(input,DOT,FOLLOW_DOT_in_synpred242_Java5845); if (state.failed) return ;

        match(input,Identifier,FOLLOW_Identifier_in_synpred242_Java5847); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred242_Java

    // $ANTLR start synpred243_Java
    public final void synpred243_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:38: ( identifierSuffix )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:898:38: identifierSuffix
        {
        pushFollow(FOLLOW_identifierSuffix_in_synpred243_Java5851);
        identifierSuffix();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred243_Java

    // $ANTLR start synpred249_Java
    public final void synpred249_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:905:10: ( '[' expression ']' )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:905:10: '[' expression ']'
        {
        match(input,79,FOLLOW_79_in_synpred249_Java5944); if (state.failed) return ;

        pushFollow(FOLLOW_expression_in_synpred249_Java5946);
        expression();

        state._fsp--;
        if (state.failed) return ;

        match(input,80,FOLLOW_80_in_synpred249_Java5948); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred249_Java

    // $ANTLR start synpred262_Java
    public final void synpred262_Java_fragment() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:931:29: ( '[' expression ']' )
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:931:29: '[' expression ']'
        {
        match(input,79,FOLLOW_79_in_synpred262_Java6184); if (state.failed) return ;

        pushFollow(FOLLOW_expression_in_synpred262_Java6186);
        expression();

        state._fsp--;
        if (state.failed) return ;

        match(input,80,FOLLOW_80_in_synpred262_Java6188); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred262_Java

    // Delegated rules

    public final boolean synpred157_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred157_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred211_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred211_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred249_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred249_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred243_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred243_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred229_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred229_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred178_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred178_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred215_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred215_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred113_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred113_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred151_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred151_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred117_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred117_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred162_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred162_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred217_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred217_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred186_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred186_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred188_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred188_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred212_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred212_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred163_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred163_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred152_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred152_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred242_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred242_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred53_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred53_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred199_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred199_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred216_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred216_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred236_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred236_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred262_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred262_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred198_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred198_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred233_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred233_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred180_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred180_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred128_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred128_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred200_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred200_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred234_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred234_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred182_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred182_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred181_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred181_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred237_Java() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred237_Java_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA122 dfa122 = new DFA122(this);
    protected DFA145 dfa145 = new DFA145(this);
    static final String DFA11_eotS =
        "\10\uffff";
    static final String DFA11_eofS =
        "\10\uffff";
    static final String DFA11_minS =
        "\1\33\2\34\1\16\1\34\1\uffff\1\16\1\uffff";
    static final String DFA11_maxS =
        "\1\33\1\55\1\34\1\53\1\54\1\uffff\1\53\1\uffff";
    static final String DFA11_acceptS =
        "\5\uffff\1\1\1\uffff\1\2";
    static final String DFA11_specialS =
        "\10\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1",
            "\1\3\20\uffff\1\2",
            "\1\3",
            "\1\4\34\uffff\1\5",
            "\1\6\17\uffff\1\7",
            "",
            "\1\4\34\uffff\1\5",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "243:1: importDeclaration : ( IMPORT ^ ( STATIC )? qualifiedName SEMI !| IMPORT ( STATIC )? qualifiedName DOT STAR SEMI -> ^( IMPORT ( STATIC )? ^( DOT qualifiedName STAR ) ) );";
        }
    }
    static final String DFA122_eotS =
        "\160\uffff";
    static final String DFA122_eofS =
        "\160\uffff";
    static final String DFA122_minS =
        "\1\15\2\34\2\16\22\uffff\1\16\3\34\1\16\1\10\1\15\1\21\31\uffff"+
        "\1\120\1\21\1\uffff\21\0\5\uffff\1\0\30\uffff\1\0\5\uffff";
    static final String DFA122_maxS =
        "\1\u0081\1\160\1\34\1\177\1\117\22\uffff\2\117\1\160\1\34\1\160"+
        "\1\164\1\u0081\1\117\31\uffff\1\120\1\117\1\uffff\21\0\5\uffff\1"+
        "\0\30\uffff\1\0\5\uffff";
    static final String DFA122_acceptS =
        "\5\uffff\1\2\137\uffff\1\1\12\uffff";
    static final String DFA122_specialS =
        "\73\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\1\15\1\16\1\17\1\20\5\uffff\1\21\30\uffff\1\22\5\uffff}>";
    static final String[] DFA122_transitionS = {
            "\1\5\1\uffff\1\5\6\uffff\1\5\2\uffff\1\5\2\uffff\1\3\11\uffff"+
            "\1\5\4\uffff\1\5\2\uffff\2\5\5\uffff\1\5\1\uffff\1\5\6\uffff"+
            "\1\5\2\uffff\2\5\2\uffff\2\5\7\uffff\1\2\5\uffff\1\4\1\uffff"+
            "\1\4\2\uffff\1\4\3\uffff\1\4\1\uffff\1\5\1\1\1\uffff\1\4\3\uffff"+
            "\1\4\1\uffff\1\4\1\uffff\2\5\4\uffff\1\4\3\uffff\1\5\3\uffff"+
            "\1\5\10\uffff\1\5",
            "\1\27\61\uffff\1\32\5\uffff\1\30\1\uffff\1\30\2\uffff\1\30"+
            "\3\uffff\1\30\2\uffff\1\31\1\uffff\1\30\3\uffff\1\30\1\uffff"+
            "\1\30\7\uffff\1\30",
            "\1\33",
            "\1\34\2\uffff\1\5\5\uffff\1\5\4\uffff\1\36\3\uffff\1\5\12\uffff"+
            "\2\5\13\uffff\7\5\1\uffff\10\5\1\uffff\2\5\1\uffff\2\5\1\uffff"+
            "\1\35\1\uffff\2\5\22\uffff\1\5\27\uffff\3\5",
            "\1\5\15\uffff\1\71\62\uffff\1\70",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\73\15\uffff\1\75\62\uffff\1\74",
            "\1\77\62\uffff\1\76",
            "\1\100\61\uffff\1\103\5\uffff\1\101\1\uffff\1\101\2\uffff\1"+
            "\101\3\uffff\1\101\2\uffff\1\102\1\uffff\1\101\3\uffff\1\101"+
            "\1\uffff\1\101\7\uffff\1\101",
            "\1\104",
            "\1\105\15\uffff\1\107\41\uffff\1\106\17\uffff\1\112\5\uffff"+
            "\1\110\1\uffff\1\110\2\uffff\1\110\3\uffff\1\110\2\uffff\1\111"+
            "\1\uffff\1\110\3\uffff\1\110\1\uffff\1\110\7\uffff\1\110",
            "\1\5\23\uffff\1\113\3\uffff\1\5\15\uffff\1\5\73\uffff\1\5\11"+
            "\uffff\1\5",
            "\1\5\1\uffff\1\5\6\uffff\1\5\2\uffff\1\5\2\uffff\1\5\11\uffff"+
            "\1\5\7\uffff\2\5\5\uffff\1\5\1\uffff\1\5\6\uffff\1\5\2\uffff"+
            "\2\5\2\uffff\2\5\11\uffff\1\121\3\uffff\1\5\1\uffff\1\5\2\uffff"+
            "\1\5\3\uffff\1\5\1\uffff\1\5\2\uffff\1\5\3\uffff\1\5\1\uffff"+
            "\1\5\1\uffff\2\5\4\uffff\1\5\3\uffff\1\5\3\uffff\1\5\10\uffff"+
            "\1\5",
            "\1\5\31\uffff\1\5\30\uffff\1\5\6\uffff\1\145\3\uffff\1\5",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\152",
            "\1\5\31\uffff\1\5\30\uffff\1\5\6\uffff\1\145\3\uffff\1\5",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA122_eot = DFA.unpackEncodedString(DFA122_eotS);
    static final short[] DFA122_eof = DFA.unpackEncodedString(DFA122_eofS);
    static final char[] DFA122_min = DFA.unpackEncodedStringToUnsignedChars(DFA122_minS);
    static final char[] DFA122_max = DFA.unpackEncodedStringToUnsignedChars(DFA122_maxS);
    static final short[] DFA122_accept = DFA.unpackEncodedString(DFA122_acceptS);
    static final short[] DFA122_special = DFA.unpackEncodedString(DFA122_specialS);
    static final short[][] DFA122_transition;

    static {
        int numStates = DFA122_transitionS.length;
        DFA122_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA122_transition[i] = DFA.unpackEncodedString(DFA122_transitionS[i]);
        }
    }

    class DFA122 extends DFA {

        public DFA122(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 122;
            this.eot = DFA122_eot;
            this.eof = DFA122_eof;
            this.min = DFA122_min;
            this.max = DFA122_max;
            this.accept = DFA122_accept;
            this.special = DFA122_special;
            this.transition = DFA122_transition;
        }
        public String getDescription() {
            return "728:1: forControl options {k=3; } : ( enhancedForControl | ( forInit )? ';' ( expression )? ';' ( forUpdate )? );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA122_59 = input.LA(1);

                         
                        int index122_59 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_59);

                        if ( s>=0 ) return s;
                        break;

                    case 1 : 
                        int LA122_60 = input.LA(1);

                         
                        int index122_60 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_60);

                        if ( s>=0 ) return s;
                        break;

                    case 2 : 
                        int LA122_61 = input.LA(1);

                         
                        int index122_61 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_61);

                        if ( s>=0 ) return s;
                        break;

                    case 3 : 
                        int LA122_62 = input.LA(1);

                         
                        int index122_62 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_62);

                        if ( s>=0 ) return s;
                        break;

                    case 4 : 
                        int LA122_63 = input.LA(1);

                         
                        int index122_63 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_63);

                        if ( s>=0 ) return s;
                        break;

                    case 5 : 
                        int LA122_64 = input.LA(1);

                         
                        int index122_64 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_64);

                        if ( s>=0 ) return s;
                        break;

                    case 6 : 
                        int LA122_65 = input.LA(1);

                         
                        int index122_65 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_65);

                        if ( s>=0 ) return s;
                        break;

                    case 7 : 
                        int LA122_66 = input.LA(1);

                         
                        int index122_66 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_66);

                        if ( s>=0 ) return s;
                        break;

                    case 8 : 
                        int LA122_67 = input.LA(1);

                         
                        int index122_67 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_67);

                        if ( s>=0 ) return s;
                        break;

                    case 9 : 
                        int LA122_68 = input.LA(1);

                         
                        int index122_68 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_68);

                        if ( s>=0 ) return s;
                        break;

                    case 10 : 
                        int LA122_69 = input.LA(1);

                         
                        int index122_69 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_69);

                        if ( s>=0 ) return s;
                        break;

                    case 11 : 
                        int LA122_70 = input.LA(1);

                         
                        int index122_70 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_70);

                        if ( s>=0 ) return s;
                        break;

                    case 12 : 
                        int LA122_71 = input.LA(1);

                         
                        int index122_71 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_71);

                        if ( s>=0 ) return s;
                        break;

                    case 13 : 
                        int LA122_72 = input.LA(1);

                         
                        int index122_72 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_72);

                        if ( s>=0 ) return s;
                        break;

                    case 14 : 
                        int LA122_73 = input.LA(1);

                         
                        int index122_73 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_73);

                        if ( s>=0 ) return s;
                        break;

                    case 15 : 
                        int LA122_74 = input.LA(1);

                         
                        int index122_74 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_74);

                        if ( s>=0 ) return s;
                        break;

                    case 16 : 
                        int LA122_75 = input.LA(1);

                         
                        int index122_75 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_75);

                        if ( s>=0 ) return s;
                        break;

                    case 17 : 
                        int LA122_81 = input.LA(1);

                         
                        int index122_81 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_81);

                        if ( s>=0 ) return s;
                        break;

                    case 18 : 
                        int LA122_106 = input.LA(1);

                         
                        int index122_106 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred182_Java()) ) {s = 101;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index122_106);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 122, _s, input);
            error(nvae);
            throw nvae;
        }

    }
    static final String DFA145_eotS =
        "\7\uffff";
    static final String DFA145_eofS =
        "\7\uffff";
    static final String DFA145_minS =
        "\1\15\1\0\1\16\2\uffff\1\120\1\16";
    static final String DFA145_maxS =
        "\1\u0081\1\0\1\117\2\uffff\1\120\1\117";
    static final String DFA145_acceptS =
        "\3\uffff\1\2\1\1\2\uffff";
    static final String DFA145_specialS =
        "\1\uffff\1\0\5\uffff}>";
    static final String[] DFA145_transitionS = {
            "\1\3\1\uffff\1\3\6\uffff\1\3\2\uffff\1\3\2\uffff\1\1\11\uffff"+
            "\1\3\7\uffff\2\3\5\uffff\1\3\1\uffff\1\3\6\uffff\1\3\2\uffff"+
            "\2\3\2\uffff\2\3\15\uffff\1\2\1\uffff\1\2\2\uffff\1\2\3\uffff"+
            "\1\2\1\uffff\1\3\2\uffff\1\2\3\uffff\1\2\1\uffff\1\2\1\uffff"+
            "\2\3\4\uffff\1\2\3\uffff\1\3\3\uffff\1\3\10\uffff\1\3",
            "\1\uffff",
            "\1\3\60\uffff\1\4\17\uffff\1\5",
            "",
            "",
            "\1\6",
            "\1\3\60\uffff\1\4\17\uffff\1\5"
    };

    static final short[] DFA145_eot = DFA.unpackEncodedString(DFA145_eotS);
    static final short[] DFA145_eof = DFA.unpackEncodedString(DFA145_eofS);
    static final char[] DFA145_min = DFA.unpackEncodedStringToUnsignedChars(DFA145_minS);
    static final char[] DFA145_max = DFA.unpackEncodedStringToUnsignedChars(DFA145_maxS);
    static final short[] DFA145_accept = DFA.unpackEncodedString(DFA145_acceptS);
    static final short[] DFA145_special = DFA.unpackEncodedString(DFA145_specialS);
    static final short[][] DFA145_transition;

    static {
        int numStates = DFA145_transitionS.length;
        DFA145_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA145_transition[i] = DFA.unpackEncodedString(DFA145_transitionS[i]);
        }
    }

    class DFA145 extends DFA {

        public DFA145(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 145;
            this.eot = DFA145_eot;
            this.eof = DFA145_eof;
            this.min = DFA145_min;
            this.max = DFA145_max;
            this.accept = DFA145_accept;
            this.special = DFA145_special;
            this.transition = DFA145_transition;
        }
        public String getDescription() {
            return "889:12: ( type | expression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA145_1 = input.LA(1);

                         
                        int index145_1 = input.index();
                        input.rewind();

                        s = -1;
                        if ( (synpred234_Java()) ) {s = 4;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index145_1);

                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}

            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 145, _s, input);
            error(nvae);
            throw nvae;
        }

    }
 

    public static final BitSet FOLLOW_annotations_in_compilationUnit282 = new BitSet(new long[]{0x0000208000010100L,0x0002708100084000L});
    public static final BitSet FOLLOW_packageDeclaration_in_compilationUnit296 = new BitSet(new long[]{0x0000280008010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_importDeclaration_in_compilationUnit298 = new BitSet(new long[]{0x0000280008010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit301 = new BitSet(new long[]{0x0000280000010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_compilationUnit316 = new BitSet(new long[]{0x0000280000010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit318 = new BitSet(new long[]{0x0000280000010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_packageDeclaration_in_compilationUnit339 = new BitSet(new long[]{0x0000280008010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_importDeclaration_in_compilationUnit342 = new BitSet(new long[]{0x0000280008010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_typeDeclaration_in_compilationUnit345 = new BitSet(new long[]{0x0000280000010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_PACKAGE_in_packageDeclaration365 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_qualifiedName_in_packageDeclaration368 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_packageDeclaration370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_importDeclaration394 = new BitSet(new long[]{0x0000200010000000L});
    public static final BitSet FOLLOW_STATIC_in_importDeclaration397 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_qualifiedName_in_importDeclaration400 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_importDeclaration403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_importDeclaration414 = new BitSet(new long[]{0x0000200010000000L});
    public static final BitSet FOLLOW_STATIC_in_importDeclaration416 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_qualifiedName_in_importDeclaration419 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_DOT_in_importDeclaration421 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_STAR_in_importDeclaration423 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_importDeclaration425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_typeDeclaration465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_typeDeclaration475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceModifiers_in_classOrInterfaceDeclaration498 = new BitSet(new long[]{0x0000000000010100L,0x0000008000004000L});
    public static final BitSet FOLLOW_classDeclaration_in_classOrInterfaceDeclaration502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceDeclaration_in_classOrInterfaceDeclaration507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceModifier_in_classOrInterfaceModifiers532 = new BitSet(new long[]{0x0000200000000002L,0x0002700100084000L});
    public static final BitSet FOLLOW_annotation_in_classOrInterfaceModifier561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_110_in_classOrInterfaceModifier574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_109_in_classOrInterfaceModifier589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_108_in_classOrInterfaceModifier601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_classOrInterfaceModifier615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_classOrInterfaceModifier629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_classOrInterfaceModifier644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_113_in_classOrInterfaceModifier660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifier_in_modifiers682 = new BitSet(new long[]{0x0000200000000002L,0x048A720100084000L});
    public static final BitSet FOLLOW_normalClassDeclaration_in_classDeclaration712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumDeclaration_in_classDeclaration723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_normalClassDeclaration749 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_normalClassDeclaration751 = new BitSet(new long[]{0x0000000104040000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeParameters_in_normalClassDeclaration753 = new BitSet(new long[]{0x0000000004040000L,0x1000000000000000L});
    public static final BitSet FOLLOW_EXTENDS_in_normalClassDeclaration765 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_type_in_normalClassDeclaration767 = new BitSet(new long[]{0x0000000004000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_normalClassDeclaration780 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_typeList_in_normalClassDeclaration782 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_classBody_in_normalClassDeclaration794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LS_in_typeParameters846 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_typeParameter_in_typeParameters848 = new BitSet(new long[]{0x0000000000800000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_typeParameters851 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_typeParameter_in_typeParameters853 = new BitSet(new long[]{0x0000000000800000L,0x0000000000000010L});
    public static final BitSet FOLLOW_GT_in_typeParameters857 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_typeParameter885 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_EXTENDS_in_typeParameter888 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_typeBound_in_typeParameter890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_typeBound919 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_60_in_typeBound922 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_type_in_typeBound924 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_ENUM_in_enumDeclaration947 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_enumDeclaration949 = new BitSet(new long[]{0x0000000004000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_IMPLEMENTS_in_enumDeclaration952 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_typeList_in_enumDeclaration954 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_enumBody_in_enumDeclaration958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_enumBody977 = new BitSet(new long[]{0x0000080010000000L,0x0000000000004010L,0x0000000000000001L});
    public static final BitSet FOLLOW_enumConstants_in_enumBody979 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_68_in_enumBody982 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_enumBodyDeclarations_in_enumBody985 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_128_in_enumBody988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumConstant_in_enumConstants1007 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_enumConstants1010 = new BitSet(new long[]{0x0000000010000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_enumConstant_in_enumConstants1012 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_annotations_in_enumConstant1037 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_enumConstant1040 = new BitSet(new long[]{0x4000000000000002L,0x1000000000000000L});
    public static final BitSet FOLLOW_arguments_in_enumConstant1042 = new BitSet(new long[]{0x0000000000000002L,0x1000000000000000L});
    public static final BitSet FOLLOW_classBody_in_enumConstant1045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_enumBodyDeclarations1069 = new BitSet(new long[]{0x0020280110010102L,0x148B73C522584000L});
    public static final BitSet FOLLOW_classBodyDeclaration_in_enumBodyDeclarations1072 = new BitSet(new long[]{0x0020280110010102L,0x148B73C522584000L});
    public static final BitSet FOLLOW_normalInterfaceDeclaration_in_interfaceDeclaration1098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationTypeDeclaration_in_interfaceDeclaration1109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_103_in_normalInterfaceDeclaration1135 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_normalInterfaceDeclaration1137 = new BitSet(new long[]{0x0000000100040000L,0x1000000000000000L});
    public static final BitSet FOLLOW_typeParameters_in_normalInterfaceDeclaration1139 = new BitSet(new long[]{0x0000000000040000L,0x1000000000000000L});
    public static final BitSet FOLLOW_EXTENDS_in_normalInterfaceDeclaration1143 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_typeList_in_normalInterfaceDeclaration1145 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_interfaceBody_in_normalInterfaceDeclaration1149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_typeList1172 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_typeList1175 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_type_in_typeList1177 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_124_in_classBody1202 = new BitSet(new long[]{0x0020280110010100L,0x148B73C522584000L,0x0000000000000001L});
    public static final BitSet FOLLOW_classBodyDeclaration_in_classBody1204 = new BitSet(new long[]{0x0020280110010100L,0x148B73C522584000L,0x0000000000000001L});
    public static final BitSet FOLLOW_128_in_classBody1207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_interfaceBody1239 = new BitSet(new long[]{0x0020280110010100L,0x048B73C522584000L,0x0000000000000001L});
    public static final BitSet FOLLOW_interfaceBodyDeclaration_in_interfaceBody1241 = new BitSet(new long[]{0x0020280110010100L,0x048B73C522584000L,0x0000000000000001L});
    public static final BitSet FOLLOW_128_in_interfaceBody1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_classBodyDeclaration1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_classBodyDeclaration1273 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_block_in_classBodyDeclaration1276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_classBodyDeclaration1286 = new BitSet(new long[]{0x0020000110010100L,0x000101C422504000L});
    public static final BitSet FOLLOW_memberDecl_in_classBodyDeclaration1289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genericMethodOrConstructorDecl_in_memberDecl1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_memberDeclaration_in_memberDecl1325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_memberDecl1336 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_memberDecl1338 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_voidMethodDeclaratorRest_in_memberDecl1340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_memberDecl1364 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_constructorDeclaratorRest_in_memberDecl1366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceDeclaration_in_memberDecl1388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classDeclaration_in_memberDecl1399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_memberDeclaration1424 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_methodDeclaration_in_memberDeclaration1426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_memberDeclaration1449 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_fieldDeclaration_in_memberDeclaration1451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeParameters_in_genericMethodOrConstructorDecl1483 = new BitSet(new long[]{0x0020000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_genericMethodOrConstructorRest_in_genericMethodOrConstructorDecl1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_genericMethodOrConstructorRest1511 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_genericMethodOrConstructorRest1513 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_methodDeclaratorRest_in_genericMethodOrConstructorRest1515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_genericMethodOrConstructorRest1542 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_genericMethodOrConstructorRest1544 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_methodDeclaratorRest_in_genericMethodOrConstructorRest1546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_genericMethodOrConstructorRest1573 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_constructorDeclaratorRest_in_genericMethodOrConstructorRest1575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_methodDeclaration1609 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_methodDeclaratorRest_in_methodDeclaration1611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclarators_in_fieldDeclaration1630 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_fieldDeclaration1632 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_interfaceBodyDeclaration1659 = new BitSet(new long[]{0x0020000110010100L,0x000101C422504000L});
    public static final BitSet FOLLOW_interfaceMemberDecl_in_interfaceBodyDeclaration1661 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_interfaceBodyDeclaration1672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceMethodOrFieldDecl_in_interfaceMemberDecl1692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceGenericMethodDecl_in_interfaceMemberDecl1702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_interfaceMemberDecl1712 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_interfaceMemberDecl1714 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_voidInterfaceMethodDeclaratorRest_in_interfaceMemberDecl1716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceDeclaration_in_interfaceMemberDecl1726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classDeclaration_in_interfaceMemberDecl1737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_interfaceMethodOrFieldDecl1761 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_interfaceMethodOrFieldDecl1763 = new BitSet(new long[]{0x4000000000020000L,0x0000000000008000L});
    public static final BitSet FOLLOW_interfaceMethodOrFieldRest_in_interfaceMethodOrFieldDecl1765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constantDeclaratorsRest_in_interfaceMethodOrFieldRest1788 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_interfaceMethodOrFieldRest1790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_interfaceMethodDeclaratorRest_in_interfaceMethodOrFieldRest1800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_formalParameters_in_methodDeclaratorRest1823 = new BitSet(new long[]{0x0000080000000000L,0x1040000000008000L});
    public static final BitSet FOLLOW_79_in_methodDeclaratorRest1826 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_methodDeclaratorRest1828 = new BitSet(new long[]{0x0000080000000000L,0x1040000000008000L});
    public static final BitSet FOLLOW_118_in_methodDeclaratorRest1841 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_qualifiedNameList_in_methodDeclaratorRest1843 = new BitSet(new long[]{0x0000080000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_methodBody_in_methodDeclaratorRest1859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_methodDeclaratorRest1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_formalParameters_in_voidMethodDeclaratorRest1906 = new BitSet(new long[]{0x0000080000000000L,0x1040000000000000L});
    public static final BitSet FOLLOW_118_in_voidMethodDeclaratorRest1909 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_qualifiedNameList_in_voidMethodDeclaratorRest1911 = new BitSet(new long[]{0x0000080000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_methodBody_in_voidMethodDeclaratorRest1927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_voidMethodDeclaratorRest1941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_formalParameters_in_interfaceMethodDeclaratorRest1974 = new BitSet(new long[]{0x0000080000000000L,0x0040000000008000L});
    public static final BitSet FOLLOW_79_in_interfaceMethodDeclaratorRest1977 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_interfaceMethodDeclaratorRest1979 = new BitSet(new long[]{0x0000080000000000L,0x0040000000008000L});
    public static final BitSet FOLLOW_118_in_interfaceMethodDeclaratorRest1984 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_qualifiedNameList_in_interfaceMethodDeclaratorRest1986 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_interfaceMethodDeclaratorRest1990 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_typeParameters_in_interfaceGenericMethodDecl2013 = new BitSet(new long[]{0x0020000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_type_in_interfaceGenericMethodDecl2016 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_VOID_in_interfaceGenericMethodDecl2020 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_interfaceGenericMethodDecl2023 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_interfaceMethodDeclaratorRest_in_interfaceGenericMethodDecl2033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_formalParameters_in_voidInterfaceMethodDeclaratorRest2056 = new BitSet(new long[]{0x0000080000000000L,0x0040000000000000L});
    public static final BitSet FOLLOW_118_in_voidInterfaceMethodDeclaratorRest2059 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_qualifiedNameList_in_voidInterfaceMethodDeclaratorRest2061 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_voidInterfaceMethodDeclaratorRest2065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_formalParameters_in_constructorDeclaratorRest2088 = new BitSet(new long[]{0x0000000000000000L,0x1040000000000000L});
    public static final BitSet FOLLOW_118_in_constructorDeclaratorRest2091 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_qualifiedNameList_in_constructorDeclaratorRest2093 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_constructorBody_in_constructorDeclaratorRest2097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_constantDeclarator2116 = new BitSet(new long[]{0x0000000000020000L,0x0000000000008000L});
    public static final BitSet FOLLOW_constantDeclaratorRest_in_constantDeclarator2118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclarator_in_variableDeclarators2141 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_variableDeclarators2144 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_variableDeclarator_in_variableDeclarators2146 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_variableDeclaratorId_in_variableDeclarator2167 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_EQ_in_variableDeclarator2170 = new BitSet(new long[]{0x40A0C0401240A000L,0x11110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_variableInitializer_in_variableDeclarator2172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constantDeclaratorRest_in_constantDeclaratorsRest2197 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_constantDeclaratorsRest2200 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_constantDeclarator_in_constantDeclaratorsRest2202 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_79_in_constantDeclaratorRest2224 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_constantDeclaratorRest2226 = new BitSet(new long[]{0x0000000000020000L,0x0000000000008000L});
    public static final BitSet FOLLOW_EQ_in_constantDeclaratorRest2230 = new BitSet(new long[]{0x40A0C0401240A000L,0x11110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_variableInitializer_in_constantDeclaratorRest2232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_variableDeclaratorId2255 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_variableDeclaratorId2258 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_variableDeclaratorId2260 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_arrayInitializer_in_variableInitializer2281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_variableInitializer2291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_arrayInitializer2318 = new BitSet(new long[]{0x40A0C0401240A000L,0x11110D44A2500066L,0x0000000000000003L});
    public static final BitSet FOLLOW_variableInitializer_in_arrayInitializer2321 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_68_in_arrayInitializer2324 = new BitSet(new long[]{0x40A0C0401240A000L,0x11110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_variableInitializer_in_arrayInitializer2326 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_68_in_arrayInitializer2331 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_128_in_arrayInitializer2338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_modifier2357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_110_in_modifier2367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_109_in_modifier2377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_108_in_modifier2387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STATIC_in_modifier2398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_modifier2408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_96_in_modifier2418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_105_in_modifier2428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_115_in_modifier2438 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_119_in_modifier2448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_122_in_modifier2458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_113_in_modifier2468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedName_in_packageOrTypeName2487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_enumConstantName2506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedName_in_typeName2525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceType_in_type2539 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_type2542 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_type2544 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_primitiveType_in_type2566 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_type2569 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_type2571 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_Identifier_in_classOrInterfaceType2601 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_DOT_in_classOrInterfaceType2613 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_classOrInterfaceType2617 = new BitSet(new long[]{0x0000000100004002L});
    public static final BitSet FOLLOW_typeArguments_in_classOrInterfaceType2619 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_96_in_variableModifier2740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_variableModifier2750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LS_in_typeArguments2769 = new BitSet(new long[]{0x0000000010000000L,0x0001014422502000L});
    public static final BitSet FOLLOW_typeArgument_in_typeArguments2771 = new BitSet(new long[]{0x0000000000800000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_typeArguments2774 = new BitSet(new long[]{0x0000000010000000L,0x0001014422502000L});
    public static final BitSet FOLLOW_typeArgument_in_typeArguments2776 = new BitSet(new long[]{0x0000000000800000L,0x0000000000000010L});
    public static final BitSet FOLLOW_GT_in_typeArguments2780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_typeArgument2803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_typeArgument2813 = new BitSet(new long[]{0x0000400000040002L});
    public static final BitSet FOLLOW_set_in_typeArgument2816 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_type_in_typeArgument2824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_qualifiedName_in_qualifiedNameList2849 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_qualifiedNameList2852 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_qualifiedName_in_qualifiedNameList2854 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_62_in_formalParameters2875 = new BitSet(new long[]{0x8000000010000000L,0x0001014522504000L});
    public static final BitSet FOLLOW_formalParameterDecls_in_formalParameters2877 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_formalParameters2880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableModifiers_in_formalParameterDecls2912 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_type_in_formalParameterDecls2915 = new BitSet(new long[]{0x0000000010000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_formalParameterDeclsRest_in_formalParameterDecls2918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclaratorId_in_formalParameterDeclsRest2943 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_formalParameterDeclsRest2946 = new BitSet(new long[]{0x0000000010000000L,0x0001014522504000L});
    public static final BitSet FOLLOW_formalParameterDecls_in_formalParameterDeclsRest2948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_formalParameterDeclsRest2979 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_variableDeclaratorId_in_formalParameterDeclsRest2981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_methodBody3018 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_constructorBody3037 = new BitSet(new long[]{0x40A0E8411241A140L,0x1B3FFDDDB6784066L,0x0000000000000003L});
    public static final BitSet FOLLOW_explicitConstructorInvocation_in_constructorBody3039 = new BitSet(new long[]{0x40A0E8401241A140L,0x1B3FFDDDB6784066L,0x0000000000000003L});
    public static final BitSet FOLLOW_blockStatement_in_constructorBody3042 = new BitSet(new long[]{0x40A0E8401241A140L,0x1B3FFDDDB6784066L,0x0000000000000003L});
    public static final BitSet FOLLOW_128_in_constructorBody3045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation3064 = new BitSet(new long[]{0x0000400000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_set_in_explicitConstructorInvocation3067 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_arguments_in_explicitConstructorInvocation3075 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_explicitConstructorInvocation3077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_explicitConstructorInvocation3087 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_DOT_in_explicitConstructorInvocation3089 = new BitSet(new long[]{0x0000400100000000L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_explicitConstructorInvocation3091 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_SUPER_in_explicitConstructorInvocation3094 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_arguments_in_explicitConstructorInvocation3096 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_explicitConstructorInvocation3098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_qualifiedName3119 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_DOT_in_qualifiedName3127 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_qualifiedName3131 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_integerLiteral_in_literal3169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FloatingPointLiteral_in_literal3179 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CharacterLiteral_in_literal3189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_StringLiteral_in_literal3199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanLiteral_in_literal3209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_107_in_literal3219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_annotations3308 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_annotation3329 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_annotationName_in_annotation3331 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_62_in_annotation3335 = new BitSet(new long[]{0xC0A0C0401240A000L,0x11110D44A2504066L,0x0000000000000002L});
    public static final BitSet FOLLOW_elementValuePairs_in_annotation3339 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_elementValue_in_annotation3343 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_annotation3348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_annotationName3381 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_DOT_in_annotationName3384 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_annotationName3386 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_elementValuePair_in_elementValuePairs3407 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_elementValuePairs3410 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_elementValuePair_in_elementValuePairs3412 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_Identifier_in_elementValuePair3433 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQ_in_elementValuePair3435 = new BitSet(new long[]{0x40A0C0401240A000L,0x11110D44A2504066L,0x0000000000000002L});
    public static final BitSet FOLLOW_elementValue_in_elementValuePair3437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpression_in_elementValue3460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_elementValue3470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elementValueArrayInitializer_in_elementValue3480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_elementValueArrayInitializer3503 = new BitSet(new long[]{0x40A0C0401240A000L,0x11110D44A2504076L,0x0000000000000003L});
    public static final BitSet FOLLOW_elementValue_in_elementValueArrayInitializer3506 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_68_in_elementValueArrayInitializer3509 = new BitSet(new long[]{0x40A0C0401240A000L,0x11110D44A2504066L,0x0000000000000002L});
    public static final BitSet FOLLOW_elementValue_in_elementValueArrayInitializer3511 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_68_in_elementValueArrayInitializer3518 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_128_in_elementValueArrayInitializer3522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_annotationTypeDeclaration3547 = new BitSet(new long[]{0x0000000000000000L,0x0000008000000000L});
    public static final BitSet FOLLOW_103_in_annotationTypeDeclaration3549 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_annotationTypeDeclaration3551 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_annotationTypeBody_in_annotationTypeDeclaration3553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_annotationTypeBody3576 = new BitSet(new long[]{0x0000200010010100L,0x048B73C522584000L,0x0000000000000001L});
    public static final BitSet FOLLOW_annotationTypeElementDeclaration_in_annotationTypeBody3579 = new BitSet(new long[]{0x0000200010010100L,0x048B73C522584000L,0x0000000000000001L});
    public static final BitSet FOLLOW_128_in_annotationTypeBody3583 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modifiers_in_annotationTypeElementDeclaration3606 = new BitSet(new long[]{0x0000000010010100L,0x000101C422504000L});
    public static final BitSet FOLLOW_annotationTypeElementRest_in_annotationTypeElementDeclaration3608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_annotationTypeElementRest3633 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_annotationMethodOrConstantRest_in_annotationTypeElementRest3635 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_annotationTypeElementRest3637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_normalClassDeclaration_in_annotationTypeElementRest3647 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_SEMI_in_annotationTypeElementRest3650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_normalInterfaceDeclaration_in_annotationTypeElementRest3661 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_SEMI_in_annotationTypeElementRest3664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enumDeclaration_in_annotationTypeElementRest3675 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_SEMI_in_annotationTypeElementRest3678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationTypeDeclaration_in_annotationTypeElementRest3689 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_SEMI_in_annotationTypeElementRest3692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationMethodRest_in_annotationMethodOrConstantRest3716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotationConstantRest_in_annotationMethodOrConstantRest3726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_annotationMethodRest3749 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_annotationMethodRest3751 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_annotationMethodRest3753 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_defaultValue_in_annotationMethodRest3755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableDeclarators_in_annotationConstantRest3779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_defaultValue3802 = new BitSet(new long[]{0x40A0C0401240A000L,0x11110D44A2504066L,0x0000000000000002L});
    public static final BitSet FOLLOW_elementValue_in_defaultValue3804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_124_in_block3825 = new BitSet(new long[]{0x40A0E8401241A140L,0x1B3FFDDDB6784066L,0x0000000000000003L});
    public static final BitSet FOLLOW_blockStatement_in_block3827 = new BitSet(new long[]{0x40A0E8401241A140L,0x1B3FFDDDB6784066L,0x0000000000000003L});
    public static final BitSet FOLLOW_128_in_block3830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclarationStatement_in_blockStatement3862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_blockStatement3872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_blockStatement3882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclaration_in_localVariableDeclarationStatement3906 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_localVariableDeclarationStatement3908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableModifiers_in_localVariableDeclaration3927 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_type_in_localVariableDeclaration3929 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_variableDeclarators_in_localVariableDeclaration3931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableModifier_in_variableModifiers3967 = new BitSet(new long[]{0x0000000000000002L,0x0000000100004000L});
    public static final BitSet FOLLOW_block_in_statement3985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSERT_in_statement3995 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_statement3997 = new BitSet(new long[]{0x0000080000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_statement4000 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_statement4002 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_statement4006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_100_in_statement4016 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_parExpression_in_statement4018 = new BitSet(new long[]{0x40A0C8401240A040L,0x1B3D8D5CB6700066L,0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statement4020 = new BitSet(new long[]{0x0000000000000002L,0x0000000040000000L});
    public static final BitSet FOLLOW_94_in_statement4030 = new BitSet(new long[]{0x40A0C8401240A040L,0x1B3D8D5CB6700066L,0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statement4032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_99_in_statement4044 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_statement4046 = new BitSet(new long[]{0x40A0C8401240A000L,0x01110D45A2504066L,0x0000000000000002L});
    public static final BitSet FOLLOW_forControl_in_statement4048 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_statement4050 = new BitSet(new long[]{0x40A0C8401240A040L,0x1B3D8D5CB6700066L,0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statement4052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_123_in_statement4062 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_parExpression_in_statement4064 = new BitSet(new long[]{0x40A0C8401240A040L,0x1B3D8D5CB6700066L,0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statement4066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_statement4076 = new BitSet(new long[]{0x40A0C8401240A040L,0x1B3D8D5CB6700066L,0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statement4078 = new BitSet(new long[]{0x0000000000000000L,0x0800000000000000L});
    public static final BitSet FOLLOW_123_in_statement4080 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_parExpression_in_statement4082 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_statement4084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_121_in_statement4094 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_block_in_statement4096 = new BitSet(new long[]{0x0000000000000000L,0x0000000201000000L});
    public static final BitSet FOLLOW_catches_in_statement4108 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_97_in_statement4110 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_block_in_statement4112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catches_in_statement4124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_97_in_statement4138 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_block_in_statement4140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_statement4160 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_parExpression_in_statement4162 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_124_in_statement4164 = new BitSet(new long[]{0x0000000000000000L,0x0000000008800000L,0x0000000000000001L});
    public static final BitSet FOLLOW_switchBlockStatementGroups_in_statement4166 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_128_in_statement4168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_115_in_statement4178 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_parExpression_in_statement4180 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_block_in_statement4182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_111_in_statement4192 = new BitSet(new long[]{0x40A0C8401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_statement4194 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_statement4197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_117_in_statement4207 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_statement4209 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_statement4211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_statement4221 = new BitSet(new long[]{0x0000080010000000L});
    public static final BitSet FOLLOW_Identifier_in_statement4223 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_statement4226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_statement4236 = new BitSet(new long[]{0x0000080010000000L});
    public static final BitSet FOLLOW_Identifier_in_statement4238 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_statement4241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMI_in_statement4251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statementExpression_in_statement4262 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_statement4264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_statement4274 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_statement4276 = new BitSet(new long[]{0x40A0C8401240A040L,0x1B3D8D5CB6700066L,0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statement4278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catchClause_in_catches4301 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_catchClause_in_catches4304 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_catchClause4329 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_catchClause4331 = new BitSet(new long[]{0x0000000010000000L,0x0001014522504000L});
    public static final BitSet FOLLOW_formalParameter_in_catchClause4333 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_catchClause4335 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_block_in_catchClause4337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableModifiers_in_formalParameter4356 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_type_in_formalParameter4358 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_variableDeclaratorId_in_formalParameter4360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switchBlockStatementGroup_in_switchBlockStatementGroups4401 = new BitSet(new long[]{0x0000000000000002L,0x0000000008800000L});
    public static final BitSet FOLLOW_switchLabel_in_switchBlockStatementGroup4428 = new BitSet(new long[]{0x40A0E8401241A142L,0x1B3FFDDDBEF84066L,0x0000000000000002L});
    public static final BitSet FOLLOW_blockStatement_in_switchBlockStatementGroup4431 = new BitSet(new long[]{0x40A0E8401241A142L,0x1B3FFDDDB6784066L,0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_switchLabel4455 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_constantExpression_in_switchLabel4457 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_switchLabel4459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_switchLabel4469 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_enumConstantName_in_switchLabel4471 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_switchLabel4473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_switchLabel4483 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_switchLabel4485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enhancedForControl_in_forControl4516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forInit_in_forControl4526 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_forControl4529 = new BitSet(new long[]{0x40A0C8401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_forControl4531 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_forControl4534 = new BitSet(new long[]{0x40A0C0401240A002L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_forUpdate_in_forControl4536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclaration_in_forInit4556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionList_in_forInit4566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variableModifiers_in_enhancedForControl4589 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_type_in_enhancedForControl4591 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_enhancedForControl4593 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_enhancedForControl4595 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_enhancedForControl4597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expressionList_in_forUpdate4616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_parExpression4637 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_parExpression4639 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_parExpression4641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList4664 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_expressionList4667 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList4669 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_expression_in_statementExpression4690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_constantExpression4713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalExpression_in_expression4736 = new BitSet(new long[]{0x2400000100820002L,0x4000000000040489L});
    public static final BitSet FOLLOW_assignmentOperator_in_expression4739 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expression4741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_assignmentOperator4766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_assignmentOperator4776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_assignmentOperator4786 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_assignmentOperator4796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_assignmentOperator4806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_assignmentOperator4816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_126_in_assignmentOperator4826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_assignmentOperator4836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_assignmentOperator4846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LS_in_assignmentOperator4867 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_LS_in_assignmentOperator4871 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQ_in_assignmentOperator4875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_assignmentOperator4909 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_assignmentOperator4913 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_assignmentOperator4917 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQ_in_assignmentOperator4921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_assignmentOperator4952 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_assignmentOperator4956 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQ_in_assignmentOperator4960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalOrExpression_in_conditionalExpression4989 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_conditionalExpression4993 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_conditionalExpression4995 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_conditionalExpression4997 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_conditionalExpression4999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression5021 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_127_in_conditionalOrExpression5025 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_conditionalAndExpression_in_conditionalOrExpression5027 = new BitSet(new long[]{0x0000000000000002L,0x8000000000000000L});
    public static final BitSet FOLLOW_inclusiveOrExpression_in_conditionalAndExpression5049 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_59_in_conditionalAndExpression5053 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_inclusiveOrExpression_in_conditionalAndExpression5055 = new BitSet(new long[]{0x0800000000000002L});
    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5077 = new BitSet(new long[]{0x0000000000000002L,0x2000000000000000L});
    public static final BitSet FOLLOW_125_in_inclusiveOrExpression5081 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_exclusiveOrExpression_in_inclusiveOrExpression5083 = new BitSet(new long[]{0x0000000000000002L,0x2000000000000000L});
    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression5105 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_exclusiveOrExpression5109 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_andExpression_in_exclusiveOrExpression5111 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_equalityExpression_in_andExpression5133 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_60_in_andExpression5137 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_equalityExpression_in_andExpression5139 = new BitSet(new long[]{0x1000000000000002L});
    public static final BitSet FOLLOW_instanceOfExpression_in_equalityExpression5161 = new BitSet(new long[]{0x0100000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_set_in_equalityExpression5165 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_instanceOfExpression_in_equalityExpression5173 = new BitSet(new long[]{0x0100000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_relationalExpression_in_instanceOfExpression5195 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
    public static final BitSet FOLLOW_101_in_instanceOfExpression5198 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_type_in_instanceOfExpression5200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression5221 = new BitSet(new long[]{0x0000000100800002L});
    public static final BitSet FOLLOW_relationalOp_in_relationalExpression5225 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_shiftExpression_in_relationalExpression5227 = new BitSet(new long[]{0x0000000100800002L});
    public static final BitSet FOLLOW_LS_in_relationalOp5262 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQ_in_relationalOp5266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_relationalOp5296 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQ_in_relationalOp5300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LS_in_relationalOp5321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_relationalOp5332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression5352 = new BitSet(new long[]{0x0000000100800002L});
    public static final BitSet FOLLOW_shiftOp_in_shiftExpression5356 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_additiveExpression_in_shiftExpression5358 = new BitSet(new long[]{0x0000000100800002L});
    public static final BitSet FOLLOW_LS_in_shiftOp5389 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_LS_in_shiftOp5393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_shiftOp5425 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_shiftOp5429 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_shiftOp5433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_shiftOp5463 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_shiftOp5467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression5497 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000022L});
    public static final BitSet FOLLOW_set_in_additiveExpression5501 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicativeExpression_in_additiveExpression5509 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000022L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression5531 = new BitSet(new long[]{0x0200100000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_set_in_multiplicativeExpression5535 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_multiplicativeExpression5549 = new BitSet(new long[]{0x0200100000000002L,0x0000000000000200L});
    public static final BitSet FOLLOW_65_in_unaryExpression5575 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression5577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_unaryExpression5587 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression5589 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_unaryExpression5599 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression5601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_unaryExpression5611 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpression5613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_unaryExpression5623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_129_in_unaryExpressionNotPlusMinus5642 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus5644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_unaryExpressionNotPlusMinus5654 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_unaryExpressionNotPlusMinus5656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_castExpression_in_unaryExpressionNotPlusMinus5666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primary_in_unaryExpressionNotPlusMinus5676 = new BitSet(new long[]{0x0000000000004002L,0x0000000000008044L});
    public static final BitSet FOLLOW_selector_in_unaryExpressionNotPlusMinus5678 = new BitSet(new long[]{0x0000000000004002L,0x0000000000008044L});
    public static final BitSet FOLLOW_62_in_castExpression5704 = new BitSet(new long[]{0x0000000000000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_primitiveType_in_castExpression5706 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_castExpression5708 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_castExpression5710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_castExpression5719 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_castExpression5722 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_expression_in_castExpression5726 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_castExpression5729 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500000L,0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpressionNotPlusMinus_in_castExpression5731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parExpression_in_primary5750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_116_in_primary5760 = new BitSet(new long[]{0x4000000000004002L,0x0000000000008000L});
    public static final BitSet FOLLOW_DOT_in_primary5763 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_primary5765 = new BitSet(new long[]{0x4000000000004002L,0x0000000000008000L});
    public static final BitSet FOLLOW_identifierSuffix_in_primary5769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUPER_in_primary5798 = new BitSet(new long[]{0x4000000000004000L});
    public static final BitSet FOLLOW_superSuffix_in_primary5800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_primary5820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_106_in_primary5830 = new BitSet(new long[]{0x0000000110000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_creator_in_primary5832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_primary5842 = new BitSet(new long[]{0x4000000000004002L,0x0000000000008000L});
    public static final BitSet FOLLOW_DOT_in_primary5845 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_primary5847 = new BitSet(new long[]{0x4000000000004002L,0x0000000000008000L});
    public static final BitSet FOLLOW_identifierSuffix_in_primary5851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_primary5880 = new BitSet(new long[]{0x0000000000004000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_primary5883 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_primary5885 = new BitSet(new long[]{0x0000000000004000L,0x0000000000008000L});
    public static final BitSet FOLLOW_DOT_in_primary5889 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_CLASS_in_primary5891 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VOID_in_primary5901 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_DOT_in_primary5903 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_CLASS_in_primary5905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_identifierSuffix5925 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_identifierSuffix5927 = new BitSet(new long[]{0x0000000000004000L,0x0000000000008000L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix5931 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_CLASS_in_identifierSuffix5933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_identifierSuffix5944 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_identifierSuffix5946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_identifierSuffix5948 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_arguments_in_identifierSuffix5961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix5971 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_CLASS_in_identifierSuffix5973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix5983 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_explicitGenericInvocation_in_identifierSuffix5985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix5995 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_identifierSuffix5997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix6007 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_SUPER_in_identifierSuffix6009 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_arguments_in_identifierSuffix6011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_identifierSuffix6021 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_identifierSuffix6023 = new BitSet(new long[]{0x0000000110000000L});
    public static final BitSet FOLLOW_innerCreator_in_identifierSuffix6025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_creator6044 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_createdName_in_creator6046 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_classCreatorRest_in_creator6048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_createdName_in_creator6058 = new BitSet(new long[]{0x4000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_arrayCreatorRest_in_creator6061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classCreatorRest_in_creator6065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceType_in_createdName6085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_primitiveType_in_createdName6095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_innerCreator6118 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_innerCreator6121 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_classCreatorRest_in_innerCreator6123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_arrayCreatorRest6142 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2510066L,0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_arrayCreatorRest6156 = new BitSet(new long[]{0x0000000000000000L,0x1000000000008000L});
    public static final BitSet FOLLOW_79_in_arrayCreatorRest6159 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_arrayCreatorRest6161 = new BitSet(new long[]{0x0000000000000000L,0x1000000000008000L});
    public static final BitSet FOLLOW_arrayInitializer_in_arrayCreatorRest6165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_arrayCreatorRest6179 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_arrayCreatorRest6181 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_arrayCreatorRest6184 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_arrayCreatorRest6186 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_arrayCreatorRest6188 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_arrayCreatorRest6193 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_arrayCreatorRest6195 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008000L});
    public static final BitSet FOLLOW_arguments_in_classCreatorRest6226 = new BitSet(new long[]{0x0000000000000002L,0x1000000000000000L});
    public static final BitSet FOLLOW_classBody_in_classCreatorRest6228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_explicitGenericInvocation6252 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_explicitGenericInvocation6254 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_arguments_in_explicitGenericInvocation6256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LS_in_nonWildcardTypeArguments6279 = new BitSet(new long[]{0x0000000010000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_typeList_in_nonWildcardTypeArguments6281 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_nonWildcardTypeArguments6283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_selector6306 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_selector6308 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_arguments_in_selector6310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_selector6321 = new BitSet(new long[]{0x0000000000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_116_in_selector6323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_selector6333 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_SUPER_in_selector6335 = new BitSet(new long[]{0x4000000000004000L});
    public static final BitSet FOLLOW_superSuffix_in_selector6337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_selector6347 = new BitSet(new long[]{0x0000000000000000L,0x0000040000000000L});
    public static final BitSet FOLLOW_106_in_selector6349 = new BitSet(new long[]{0x0000000110000000L});
    public static final BitSet FOLLOW_innerCreator_in_selector6351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_selector6361 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_selector6363 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_selector6365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arguments_in_superSuffix6388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_superSuffix6398 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_superSuffix6400 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_arguments_in_superSuffix6402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_arguments6422 = new BitSet(new long[]{0xC0A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expressionList_in_arguments6424 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_arguments6427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotations_in_synpred5_Java282 = new BitSet(new long[]{0x0000208000010100L,0x0002708100084000L});
    public static final BitSet FOLLOW_packageDeclaration_in_synpred5_Java296 = new BitSet(new long[]{0x0000280008010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_importDeclaration_in_synpred5_Java298 = new BitSet(new long[]{0x0000280008010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_typeDeclaration_in_synpred5_Java301 = new BitSet(new long[]{0x0000280000010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_synpred5_Java316 = new BitSet(new long[]{0x0000280000010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_typeDeclaration_in_synpred5_Java318 = new BitSet(new long[]{0x0000280000010102L,0x0002708100084000L});
    public static final BitSet FOLLOW_type_in_synpred53_Java1424 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_methodDeclaration_in_synpred53_Java1426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_explicitConstructorInvocation_in_synpred113_Java3039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nonWildcardTypeArguments_in_synpred117_Java3064 = new BitSet(new long[]{0x0000400000000000L,0x0010000000000000L});
    public static final BitSet FOLLOW_set_in_synpred117_Java3067 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_arguments_in_synpred117_Java3075 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_SEMI_in_synpred117_Java3077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annotation_in_synpred128_Java3308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclarationStatement_in_synpred151_Java3862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_classOrInterfaceDeclaration_in_synpred152_Java3872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_94_in_synpred157_Java4030 = new BitSet(new long[]{0x40A0C8401240A040L,0x1B3D8D5CB6700066L,0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_synpred157_Java4032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catches_in_synpred162_Java4108 = new BitSet(new long[]{0x0000000000000000L,0x0000000200000000L});
    public static final BitSet FOLLOW_97_in_synpred162_Java4110 = new BitSet(new long[]{0x0000000000000000L,0x1000000000000000L});
    public static final BitSet FOLLOW_block_in_synpred162_Java4112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_catches_in_synpred163_Java4124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switchLabel_in_synpred178_Java4428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_synpred180_Java4455 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_constantExpression_in_synpred180_Java4457 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_synpred180_Java4459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_synpred181_Java4469 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_enumConstantName_in_synpred181_Java4471 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_synpred181_Java4473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_enhancedForControl_in_synpred182_Java4516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_localVariableDeclaration_in_synpred186_Java4556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignmentOperator_in_synpred188_Java4739 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_synpred188_Java4741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LS_in_synpred198_Java4857 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_LS_in_synpred198_Java4859 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQ_in_synpred198_Java4861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_synpred199_Java4897 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_synpred199_Java4899 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_synpred199_Java4901 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQ_in_synpred199_Java4903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_synpred200_Java4942 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_synpred200_Java4944 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQ_in_synpred200_Java4946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LS_in_synpred211_Java5254 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQ_in_synpred211_Java5256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_synpred212_Java5288 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_EQ_in_synpred212_Java5290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LS_in_synpred215_Java5381 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_LS_in_synpred215_Java5383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_synpred216_Java5415 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_synpred216_Java5417 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_synpred216_Java5419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_synpred217_Java5455 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_GT_in_synpred217_Java5457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_castExpression_in_synpred229_Java5666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_synpred233_Java5704 = new BitSet(new long[]{0x0000000000000000L,0x0001014422500000L});
    public static final BitSet FOLLOW_primitiveType_in_synpred233_Java5706 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_synpred233_Java5708 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_synpred233_Java5710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_synpred234_Java5722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_synpred236_Java5763 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_synpred236_Java5765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierSuffix_in_synpred237_Java5769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_synpred242_Java5845 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_Identifier_in_synpred242_Java5847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifierSuffix_in_synpred243_Java5851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_synpred249_Java5944 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_synpred249_Java5946 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_synpred249_Java5948 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_synpred262_Java6184 = new BitSet(new long[]{0x40A0C0401240A000L,0x01110D44A2500066L,0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_synpred262_Java6186 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_synpred262_Java6188 = new BitSet(new long[]{0x0000000000000002L});

}