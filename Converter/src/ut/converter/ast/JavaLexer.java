// $ANTLR 3.4 E:\\workspace_16_05_10\\Converter\\lib\\Java.g 2012-05-10 15:01:13

	package ut.converter.ast;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class JavaLexer extends Lexer {
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

      protected boolean enumIsKeyword = true;
      protected boolean assertIsKeyword = true;


    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public JavaLexer() {} 
    public JavaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public JavaLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "E:\\workspace_16_05_10\\Converter\\lib\\Java.g"; }

    // $ANTLR start "CLASS"
    public final void mCLASS() throws RecognitionException {
        try {
            int _type = CLASS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:10:7: ( 'class' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:10:9: 'class'
            {
            match("class"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CLASS"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:11:5: ( '.' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:11:7: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:12:4: ( '=' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:12:6: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "EXTENDS"
    public final void mEXTENDS() throws RecognitionException {
        try {
            int _type = EXTENDS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:13:9: ( 'extends' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:13:11: 'extends'
            {
            match("extends"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXTENDS"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:14:4: ( '>' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:14:6: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "IMPLEMENTS"
    public final void mIMPLEMENTS() throws RecognitionException {
        try {
            int _type = IMPLEMENTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:15:12: ( 'implements' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:15:14: 'implements'
            {
            match("implements"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IMPLEMENTS"

    // $ANTLR start "IMPORT"
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:16:8: ( 'import' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:16:10: 'import'
            {
            match("import"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IMPORT"

    // $ANTLR start "LS"
    public final void mLS() throws RecognitionException {
        try {
            int _type = LS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:17:4: ( '<' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:17:6: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LS"

    // $ANTLR start "PACKAGE"
    public final void mPACKAGE() throws RecognitionException {
        try {
            int _type = PACKAGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:18:9: ( 'package' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:18:11: 'package'
            {
            match("package"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PACKAGE"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:19:6: ( ';' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:19:8: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:20:6: ( '*' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:20:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "STATIC"
    public final void mSTATIC() throws RecognitionException {
        try {
            int _type = STATIC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:21:8: ( 'static' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:21:10: 'static'
            {
            match("static"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STATIC"

    // $ANTLR start "SUPER"
    public final void mSUPER() throws RecognitionException {
        try {
            int _type = SUPER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:22:7: ( 'super' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:22:9: 'super'
            {
            match("super"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SUPER"

    // $ANTLR start "VOID"
    public final void mVOID() throws RecognitionException {
        try {
            int _type = VOID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:23:6: ( 'void' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:23:8: 'void'
            {
            match("void"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "VOID"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:24:7: ( '!' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:24:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:25:7: ( '!=' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:25:9: '!='
            {
            match("!="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:26:7: ( '%' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:26:9: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:27:7: ( '%=' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:27:9: '%='
            {
            match("%="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:28:7: ( '&&' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:28:9: '&&'
            {
            match("&&"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:29:7: ( '&' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:29:9: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:30:7: ( '&=' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:30:9: '&='
            {
            match("&="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:31:7: ( '(' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:31:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:32:7: ( ')' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:32:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:33:7: ( '*=' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:33:9: '*='
            {
            match("*="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:34:7: ( '+' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:34:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:35:7: ( '++' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:35:9: '++'
            {
            match("++"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:36:7: ( '+=' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:36:9: '+='
            {
            match("+="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:37:7: ( ',' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:37:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:38:7: ( '-' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:38:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:39:7: ( '--' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:39:9: '--'
            {
            match("--"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:40:7: ( '-=' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:40:9: '-='
            {
            match("-="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:41:7: ( '...' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:41:9: '...'
            {
            match("..."); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:42:7: ( '/' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:42:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:43:7: ( '/=' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:43:9: '/='
            {
            match("/="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:44:7: ( ':' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:44:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:45:7: ( '==' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:45:9: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:46:7: ( '?' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:46:9: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:47:7: ( '@' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:47:9: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:48:7: ( '[' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:48:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:49:7: ( ']' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:49:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:50:7: ( '^' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:50:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:51:7: ( '^=' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:51:9: '^='
            {
            match("^="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:52:7: ( 'abstract' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:52:9: 'abstract'
            {
            match("abstract"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:53:7: ( 'boolean' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:53:9: 'boolean'
            {
            match("boolean"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:54:7: ( 'break' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:54:9: 'break'
            {
            match("break"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:55:7: ( 'byte' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:55:9: 'byte'
            {
            match("byte"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:56:7: ( 'case' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:56:9: 'case'
            {
            match("case"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:57:7: ( 'catch' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:57:9: 'catch'
            {
            match("catch"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:58:7: ( 'char' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:58:9: 'char'
            {
            match("char"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:59:7: ( 'continue' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:59:9: 'continue'
            {
            match("continue"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
    public final void mT__91() throws RecognitionException {
        try {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:60:7: ( 'default' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:60:9: 'default'
            {
            match("default"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:61:7: ( 'do' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:61:9: 'do'
            {
            match("do"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__92"

    // $ANTLR start "T__93"
    public final void mT__93() throws RecognitionException {
        try {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:62:7: ( 'double' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:62:9: 'double'
            {
            match("double"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__93"

    // $ANTLR start "T__94"
    public final void mT__94() throws RecognitionException {
        try {
            int _type = T__94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:63:7: ( 'else' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:63:9: 'else'
            {
            match("else"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__94"

    // $ANTLR start "T__95"
    public final void mT__95() throws RecognitionException {
        try {
            int _type = T__95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:64:7: ( 'false' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:64:9: 'false'
            {
            match("false"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__95"

    // $ANTLR start "T__96"
    public final void mT__96() throws RecognitionException {
        try {
            int _type = T__96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:65:7: ( 'final' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:65:9: 'final'
            {
            match("final"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__96"

    // $ANTLR start "T__97"
    public final void mT__97() throws RecognitionException {
        try {
            int _type = T__97;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:66:7: ( 'finally' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:66:9: 'finally'
            {
            match("finally"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__97"

    // $ANTLR start "T__98"
    public final void mT__98() throws RecognitionException {
        try {
            int _type = T__98;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:67:7: ( 'float' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:67:9: 'float'
            {
            match("float"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__98"

    // $ANTLR start "T__99"
    public final void mT__99() throws RecognitionException {
        try {
            int _type = T__99;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:68:7: ( 'for' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:68:9: 'for'
            {
            match("for"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__99"

    // $ANTLR start "T__100"
    public final void mT__100() throws RecognitionException {
        try {
            int _type = T__100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:69:8: ( 'if' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:69:10: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__100"

    // $ANTLR start "T__101"
    public final void mT__101() throws RecognitionException {
        try {
            int _type = T__101;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:70:8: ( 'instanceof' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:70:10: 'instanceof'
            {
            match("instanceof"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__101"

    // $ANTLR start "T__102"
    public final void mT__102() throws RecognitionException {
        try {
            int _type = T__102;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:71:8: ( 'int' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:71:10: 'int'
            {
            match("int"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__102"

    // $ANTLR start "T__103"
    public final void mT__103() throws RecognitionException {
        try {
            int _type = T__103;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:72:8: ( 'interface' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:72:10: 'interface'
            {
            match("interface"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__103"

    // $ANTLR start "T__104"
    public final void mT__104() throws RecognitionException {
        try {
            int _type = T__104;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:73:8: ( 'long' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:73:10: 'long'
            {
            match("long"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__104"

    // $ANTLR start "T__105"
    public final void mT__105() throws RecognitionException {
        try {
            int _type = T__105;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:74:8: ( 'native' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:74:10: 'native'
            {
            match("native"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__105"

    // $ANTLR start "T__106"
    public final void mT__106() throws RecognitionException {
        try {
            int _type = T__106;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:75:8: ( 'new' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:75:10: 'new'
            {
            match("new"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__106"

    // $ANTLR start "T__107"
    public final void mT__107() throws RecognitionException {
        try {
            int _type = T__107;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:76:8: ( 'null' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:76:10: 'null'
            {
            match("null"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__107"

    // $ANTLR start "T__108"
    public final void mT__108() throws RecognitionException {
        try {
            int _type = T__108;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:77:8: ( 'private' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:77:10: 'private'
            {
            match("private"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__108"

    // $ANTLR start "T__109"
    public final void mT__109() throws RecognitionException {
        try {
            int _type = T__109;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:78:8: ( 'protected' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:78:10: 'protected'
            {
            match("protected"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__109"

    // $ANTLR start "T__110"
    public final void mT__110() throws RecognitionException {
        try {
            int _type = T__110;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:79:8: ( 'public' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:79:10: 'public'
            {
            match("public"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__110"

    // $ANTLR start "T__111"
    public final void mT__111() throws RecognitionException {
        try {
            int _type = T__111;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:80:8: ( 'return' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:80:10: 'return'
            {
            match("return"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__111"

    // $ANTLR start "T__112"
    public final void mT__112() throws RecognitionException {
        try {
            int _type = T__112;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:81:8: ( 'short' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:81:10: 'short'
            {
            match("short"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__112"

    // $ANTLR start "T__113"
    public final void mT__113() throws RecognitionException {
        try {
            int _type = T__113;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:82:8: ( 'strictfp' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:82:10: 'strictfp'
            {
            match("strictfp"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__113"

    // $ANTLR start "T__114"
    public final void mT__114() throws RecognitionException {
        try {
            int _type = T__114;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:83:8: ( 'switch' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:83:10: 'switch'
            {
            match("switch"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__114"

    // $ANTLR start "T__115"
    public final void mT__115() throws RecognitionException {
        try {
            int _type = T__115;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:84:8: ( 'synchronized' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:84:10: 'synchronized'
            {
            match("synchronized"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__115"

    // $ANTLR start "T__116"
    public final void mT__116() throws RecognitionException {
        try {
            int _type = T__116;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:85:8: ( 'this' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:85:10: 'this'
            {
            match("this"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__116"

    // $ANTLR start "T__117"
    public final void mT__117() throws RecognitionException {
        try {
            int _type = T__117;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:86:8: ( 'throw' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:86:10: 'throw'
            {
            match("throw"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__117"

    // $ANTLR start "T__118"
    public final void mT__118() throws RecognitionException {
        try {
            int _type = T__118;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:87:8: ( 'throws' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:87:10: 'throws'
            {
            match("throws"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__118"

    // $ANTLR start "T__119"
    public final void mT__119() throws RecognitionException {
        try {
            int _type = T__119;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:88:8: ( 'transient' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:88:10: 'transient'
            {
            match("transient"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__119"

    // $ANTLR start "T__120"
    public final void mT__120() throws RecognitionException {
        try {
            int _type = T__120;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:89:8: ( 'true' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:89:10: 'true'
            {
            match("true"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__120"

    // $ANTLR start "T__121"
    public final void mT__121() throws RecognitionException {
        try {
            int _type = T__121;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:90:8: ( 'try' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:90:10: 'try'
            {
            match("try"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__121"

    // $ANTLR start "T__122"
    public final void mT__122() throws RecognitionException {
        try {
            int _type = T__122;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:91:8: ( 'volatile' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:91:10: 'volatile'
            {
            match("volatile"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__122"

    // $ANTLR start "T__123"
    public final void mT__123() throws RecognitionException {
        try {
            int _type = T__123;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:92:8: ( 'while' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:92:10: 'while'
            {
            match("while"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__123"

    // $ANTLR start "T__124"
    public final void mT__124() throws RecognitionException {
        try {
            int _type = T__124;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:93:8: ( '{' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:93:10: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__124"

    // $ANTLR start "T__125"
    public final void mT__125() throws RecognitionException {
        try {
            int _type = T__125;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:94:8: ( '|' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:94:10: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__125"

    // $ANTLR start "T__126"
    public final void mT__126() throws RecognitionException {
        try {
            int _type = T__126;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:95:8: ( '|=' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:95:10: '|='
            {
            match("|="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__126"

    // $ANTLR start "T__127"
    public final void mT__127() throws RecognitionException {
        try {
            int _type = T__127;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:96:8: ( '||' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:96:10: '||'
            {
            match("||"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__127"

    // $ANTLR start "T__128"
    public final void mT__128() throws RecognitionException {
        try {
            int _type = T__128;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:97:8: ( '}' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:97:10: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__128"

    // $ANTLR start "T__129"
    public final void mT__129() throws RecognitionException {
        try {
            int _type = T__129;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:98:8: ( '~' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:98:10: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__129"

    // $ANTLR start "HexLiteral"
    public final void mHexLiteral() throws RecognitionException {
        try {
            int _type = HexLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:966:12: ( '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:966:14: '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )?
            {
            match('0'); 

            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:966:28: ( HexDigit )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'F')||(LA1_0 >= 'a' && LA1_0 <= 'f')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:966:38: ( IntegerTypeSuffix )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='L'||LA2_0=='l') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                    {
                    if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexLiteral"

    // $ANTLR start "DecimalLiteral"
    public final void mDecimalLiteral() throws RecognitionException {
        try {
            int _type = DecimalLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:968:16: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:968:18: ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )?
            {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:968:18: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='0') ) {
                alt4=1;
            }
            else if ( ((LA4_0 >= '1' && LA4_0 <= '9')) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:968:19: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:968:25: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:968:34: ( '0' .. '9' )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:968:45: ( IntegerTypeSuffix )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='L'||LA5_0=='l') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                    {
                    if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DecimalLiteral"

    // $ANTLR start "OctalLiteral"
    public final void mOctalLiteral() throws RecognitionException {
        try {
            int _type = OctalLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:970:14: ( '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )? )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:970:16: '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )?
            {
            match('0'); 

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:970:20: ( '0' .. '7' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0 >= '0' && LA6_0 <= '7')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:970:32: ( IntegerTypeSuffix )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='L'||LA7_0=='l') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                    {
                    if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OctalLiteral"

    // $ANTLR start "HexDigit"
    public final void mHexDigit() throws RecognitionException {
        try {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:974:10: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HexDigit"

    // $ANTLR start "IntegerTypeSuffix"
    public final void mIntegerTypeSuffix() throws RecognitionException {
        try {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:977:19: ( ( 'l' | 'L' ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IntegerTypeSuffix"

    // $ANTLR start "FloatingPointLiteral"
    public final void mFloatingPointLiteral() throws RecognitionException {
        try {
            int _type = FloatingPointLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:979:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ FloatTypeSuffix )
            int alt18=4;
            alt18 = dfa18.predict(input);
            switch (alt18) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:979:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )?
                    {
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:979:9: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);


                    match('.'); 

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:979:25: ( '0' .. '9' )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0 >= '0' && LA9_0 <= '9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:979:37: ( Exponent )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='E'||LA10_0=='e') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:979:37: Exponent
                            {
                            mExponent(); 


                            }
                            break;

                    }


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:979:47: ( FloatTypeSuffix )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='D'||LA11_0=='F'||LA11_0=='d'||LA11_0=='f') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                            {
                            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:980:9: '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )?
                    {
                    match('.'); 

                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:980:13: ( '0' .. '9' )+
                    int cnt12=0;
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0 >= '0' && LA12_0 <= '9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt12 >= 1 ) break loop12;
                                EarlyExitException eee =
                                    new EarlyExitException(12, input);
                                throw eee;
                        }
                        cnt12++;
                    } while (true);


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:980:25: ( Exponent )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='E'||LA13_0=='e') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:980:25: Exponent
                            {
                            mExponent(); 


                            }
                            break;

                    }


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:980:35: ( FloatTypeSuffix )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='D'||LA14_0=='F'||LA14_0=='d'||LA14_0=='f') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                            {
                            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:981:9: ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )?
                    {
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:981:9: ( '0' .. '9' )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0 >= '0' && LA15_0 <= '9')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);


                    mExponent(); 


                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:981:30: ( FloatTypeSuffix )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='D'||LA16_0=='F'||LA16_0=='d'||LA16_0=='f') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                            {
                            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:982:9: ( '0' .. '9' )+ FloatTypeSuffix
                    {
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:982:9: ( '0' .. '9' )+
                    int cnt17=0;
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( ((LA17_0 >= '0' && LA17_0 <= '9')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt17 >= 1 ) break loop17;
                                EarlyExitException eee =
                                    new EarlyExitException(17, input);
                                throw eee;
                        }
                        cnt17++;
                    } while (true);


                    mFloatTypeSuffix(); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FloatingPointLiteral"

    // $ANTLR start "Exponent"
    public final void mExponent() throws RecognitionException {
        try {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:987:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:987:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:987:22: ( '+' | '-' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='+'||LA19_0=='-') ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:987:33: ( '0' .. '9' )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0 >= '0' && LA20_0 <= '9')) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Exponent"

    // $ANTLR start "FloatTypeSuffix"
    public final void mFloatTypeSuffix() throws RecognitionException {
        try {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:990:17: ( ( 'f' | 'F' | 'd' | 'D' ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            {
            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FloatTypeSuffix"

    // $ANTLR start "CharacterLiteral"
    public final void mCharacterLiteral() throws RecognitionException {
        try {
            int _type = CharacterLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:992:5: ( '\\'' ( EscapeSequence |~ ( '\\'' | '\\\\' ) ) '\\'' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:992:9: '\\'' ( EscapeSequence |~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:992:14: ( EscapeSequence |~ ( '\\'' | '\\\\' ) )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\\') ) {
                alt21=1;
            }
            else if ( ((LA21_0 >= '\u0000' && LA21_0 <= '&')||(LA21_0 >= '(' && LA21_0 <= '[')||(LA21_0 >= ']' && LA21_0 <= '\uFFFF')) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }
            switch (alt21) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:992:16: EscapeSequence
                    {
                    mEscapeSequence(); 


                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:992:33: ~ ( '\\'' | '\\\\' )
                    {
                    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CharacterLiteral"

    // $ANTLR start "StringLiteral"
    public final void mStringLiteral() throws RecognitionException {
        try {
            int _type = StringLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:996:5: ( '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:996:8: '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 

            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:996:12: ( EscapeSequence |~ ( '\\\\' | '\"' ) )*
            loop22:
            do {
                int alt22=3;
                int LA22_0 = input.LA(1);

                if ( (LA22_0=='\\') ) {
                    alt22=1;
                }
                else if ( ((LA22_0 >= '\u0000' && LA22_0 <= '!')||(LA22_0 >= '#' && LA22_0 <= '[')||(LA22_0 >= ']' && LA22_0 <= '\uFFFF')) ) {
                    alt22=2;
                }


                switch (alt22) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:996:14: EscapeSequence
            	    {
            	    mEscapeSequence(); 


            	    }
            	    break;
            	case 2 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:996:31: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "StringLiteral"

    // $ANTLR start "EscapeSequence"
    public final void mEscapeSequence() throws RecognitionException {
        try {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1002:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UnicodeEscape | OctalEscape )
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt23=1;
                    }
                    break;
                case 'u':
                    {
                    alt23=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt23=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1002:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 

                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1003:9: UnicodeEscape
                    {
                    mUnicodeEscape(); 


                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1004:9: OctalEscape
                    {
                    mOctalEscape(); 


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EscapeSequence"

    // $ANTLR start "OctalEscape"
    public final void mOctalEscape() throws RecognitionException {
        try {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1009:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt24=3;
            int LA24_0 = input.LA(1);

            if ( (LA24_0=='\\') ) {
                int LA24_1 = input.LA(2);

                if ( ((LA24_1 >= '0' && LA24_1 <= '3')) ) {
                    int LA24_2 = input.LA(3);

                    if ( ((LA24_2 >= '0' && LA24_2 <= '7')) ) {
                        int LA24_4 = input.LA(4);

                        if ( ((LA24_4 >= '0' && LA24_4 <= '7')) ) {
                            alt24=1;
                        }
                        else {
                            alt24=2;
                        }
                    }
                    else {
                        alt24=3;
                    }
                }
                else if ( ((LA24_1 >= '4' && LA24_1 <= '7')) ) {
                    int LA24_3 = input.LA(3);

                    if ( ((LA24_3 >= '0' && LA24_3 <= '7')) ) {
                        alt24=2;
                    }
                    else {
                        alt24=3;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;

            }
            switch (alt24) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1009:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '3') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1010:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;
                case 3 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1011:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 

                    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }

        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OctalEscape"

    // $ANTLR start "UnicodeEscape"
    public final void mUnicodeEscape() throws RecognitionException {
        try {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1016:5: ( '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1016:9: '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit
            {
            match('\\'); 

            match('u'); 

            mHexDigit(); 


            mHexDigit(); 


            mHexDigit(); 


            mHexDigit(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UnicodeEscape"

    // $ANTLR start "ENUM"
    public final void mENUM() throws RecognitionException {
        try {
            int _type = ENUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1018:5: ( 'enum' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1018:9: 'enum'
            {
            match("enum"); 



            if (!enumIsKeyword) _type=Identifier;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ENUM"

    // $ANTLR start "ASSERT"
    public final void mASSERT() throws RecognitionException {
        try {
            int _type = ASSERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1022:5: ( 'assert' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1022:9: 'assert'
            {
            match("assert"); 



            if (!assertIsKeyword) _type=Identifier;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ASSERT"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1026:5: ( Letter ( Letter | JavaIDDigit )* )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1026:9: Letter ( Letter | JavaIDDigit )*
            {
            mLetter(); 


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1026:16: ( Letter | JavaIDDigit )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0=='$'||(LA25_0 >= '0' && LA25_0 <= '9')||(LA25_0 >= 'A' && LA25_0 <= 'Z')||LA25_0=='_'||(LA25_0 >= 'a' && LA25_0 <= 'z')||(LA25_0 >= '\u00C0' && LA25_0 <= '\u00D6')||(LA25_0 >= '\u00D8' && LA25_0 <= '\u00F6')||(LA25_0 >= '\u00F8' && LA25_0 <= '\u1FFF')||(LA25_0 >= '\u3040' && LA25_0 <= '\u318F')||(LA25_0 >= '\u3300' && LA25_0 <= '\u337F')||(LA25_0 >= '\u3400' && LA25_0 <= '\u3D2D')||(LA25_0 >= '\u4E00' && LA25_0 <= '\u9FFF')||(LA25_0 >= '\uF900' && LA25_0 <= '\uFAFF')) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u3040' && input.LA(1) <= '\u318F')||(input.LA(1) >= '\u3300' && input.LA(1) <= '\u337F')||(input.LA(1) >= '\u3400' && input.LA(1) <= '\u3D2D')||(input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FFF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFAFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Identifier"

    // $ANTLR start "Letter"
    public final void mLetter() throws RecognitionException {
        try {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1038:5: ( '\\u0024' | '\\u0041' .. '\\u005a' | '\\u005f' | '\\u0061' .. '\\u007a' | '\\u00c0' .. '\\u00d6' | '\\u00d8' .. '\\u00f6' | '\\u00f8' .. '\\u00ff' | '\\u0100' .. '\\u1fff' | '\\u3040' .. '\\u318f' | '\\u3300' .. '\\u337f' | '\\u3400' .. '\\u3d2d' | '\\u4e00' .. '\\u9fff' | '\\uf900' .. '\\ufaff' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            {
            if ( input.LA(1)=='$'||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u3040' && input.LA(1) <= '\u318F')||(input.LA(1) >= '\u3300' && input.LA(1) <= '\u337F')||(input.LA(1) >= '\u3400' && input.LA(1) <= '\u3D2D')||(input.LA(1) >= '\u4E00' && input.LA(1) <= '\u9FFF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFAFF') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "Letter"

    // $ANTLR start "JavaIDDigit"
    public final void mJavaIDDigit() throws RecognitionException {
        try {
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1052:5: ( '\\u0030' .. '\\u0039' | '\\u0660' .. '\\u0669' | '\\u06f0' .. '\\u06f9' | '\\u0966' .. '\\u096f' | '\\u09e6' .. '\\u09ef' | '\\u0a66' .. '\\u0a6f' | '\\u0ae6' .. '\\u0aef' | '\\u0b66' .. '\\u0b6f' | '\\u0be7' .. '\\u0bef' | '\\u0c66' .. '\\u0c6f' | '\\u0ce6' .. '\\u0cef' | '\\u0d66' .. '\\u0d6f' | '\\u0e50' .. '\\u0e59' | '\\u0ed0' .. '\\u0ed9' | '\\u1040' .. '\\u1049' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= '\u0660' && input.LA(1) <= '\u0669')||(input.LA(1) >= '\u06F0' && input.LA(1) <= '\u06F9')||(input.LA(1) >= '\u0966' && input.LA(1) <= '\u096F')||(input.LA(1) >= '\u09E6' && input.LA(1) <= '\u09EF')||(input.LA(1) >= '\u0A66' && input.LA(1) <= '\u0A6F')||(input.LA(1) >= '\u0AE6' && input.LA(1) <= '\u0AEF')||(input.LA(1) >= '\u0B66' && input.LA(1) <= '\u0B6F')||(input.LA(1) >= '\u0BE7' && input.LA(1) <= '\u0BEF')||(input.LA(1) >= '\u0C66' && input.LA(1) <= '\u0C6F')||(input.LA(1) >= '\u0CE6' && input.LA(1) <= '\u0CEF')||(input.LA(1) >= '\u0D66' && input.LA(1) <= '\u0D6F')||(input.LA(1) >= '\u0E50' && input.LA(1) <= '\u0E59')||(input.LA(1) >= '\u0ED0' && input.LA(1) <= '\u0ED9')||(input.LA(1) >= '\u1040' && input.LA(1) <= '\u1049') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "JavaIDDigit"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1068:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1068:8: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1072:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1072:9: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 



            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1072:14: ( options {greedy=false; } : . )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0=='*') ) {
                    int LA26_1 = input.LA(2);

                    if ( (LA26_1=='/') ) {
                        alt26=2;
                    }
                    else if ( ((LA26_1 >= '\u0000' && LA26_1 <= '.')||(LA26_1 >= '0' && LA26_1 <= '\uFFFF')) ) {
                        alt26=1;
                    }


                }
                else if ( ((LA26_0 >= '\u0000' && LA26_0 <= ')')||(LA26_0 >= '+' && LA26_0 <= '\uFFFF')) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1072:42: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            match("*/"); 



            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1076:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1076:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 



            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1076:12: (~ ( '\\n' | '\\r' ) )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0 >= '\u0000' && LA27_0 <= '\t')||(LA27_0 >= '\u000B' && LA27_0 <= '\f')||(LA27_0 >= '\u000E' && LA27_0 <= '\uFFFF')) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


            // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1076:26: ( '\\r' )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0=='\r') ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1076:26: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LINE_COMMENT"

    public void mTokens() throws RecognitionException {
        // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:8: ( CLASS | DOT | EQ | EXTENDS | GT | IMPLEMENTS | IMPORT | LS | PACKAGE | SEMI | STAR | STATIC | SUPER | VOID | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | T__123 | T__124 | T__125 | T__126 | T__127 | T__128 | T__129 | HexLiteral | DecimalLiteral | OctalLiteral | FloatingPointLiteral | CharacterLiteral | StringLiteral | ENUM | ASSERT | Identifier | WS | COMMENT | LINE_COMMENT )
        int alt29=101;
        alt29 = dfa29.predict(input);
        switch (alt29) {
            case 1 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:10: CLASS
                {
                mCLASS(); 


                }
                break;
            case 2 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:16: DOT
                {
                mDOT(); 


                }
                break;
            case 3 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:20: EQ
                {
                mEQ(); 


                }
                break;
            case 4 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:23: EXTENDS
                {
                mEXTENDS(); 


                }
                break;
            case 5 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:31: GT
                {
                mGT(); 


                }
                break;
            case 6 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:34: IMPLEMENTS
                {
                mIMPLEMENTS(); 


                }
                break;
            case 7 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:45: IMPORT
                {
                mIMPORT(); 


                }
                break;
            case 8 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:52: LS
                {
                mLS(); 


                }
                break;
            case 9 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:55: PACKAGE
                {
                mPACKAGE(); 


                }
                break;
            case 10 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:63: SEMI
                {
                mSEMI(); 


                }
                break;
            case 11 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:68: STAR
                {
                mSTAR(); 


                }
                break;
            case 12 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:73: STATIC
                {
                mSTATIC(); 


                }
                break;
            case 13 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:80: SUPER
                {
                mSUPER(); 


                }
                break;
            case 14 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:86: VOID
                {
                mVOID(); 


                }
                break;
            case 15 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:91: T__55
                {
                mT__55(); 


                }
                break;
            case 16 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:97: T__56
                {
                mT__56(); 


                }
                break;
            case 17 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:103: T__57
                {
                mT__57(); 


                }
                break;
            case 18 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:109: T__58
                {
                mT__58(); 


                }
                break;
            case 19 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:115: T__59
                {
                mT__59(); 


                }
                break;
            case 20 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:121: T__60
                {
                mT__60(); 


                }
                break;
            case 21 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:127: T__61
                {
                mT__61(); 


                }
                break;
            case 22 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:133: T__62
                {
                mT__62(); 


                }
                break;
            case 23 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:139: T__63
                {
                mT__63(); 


                }
                break;
            case 24 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:145: T__64
                {
                mT__64(); 


                }
                break;
            case 25 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:151: T__65
                {
                mT__65(); 


                }
                break;
            case 26 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:157: T__66
                {
                mT__66(); 


                }
                break;
            case 27 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:163: T__67
                {
                mT__67(); 


                }
                break;
            case 28 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:169: T__68
                {
                mT__68(); 


                }
                break;
            case 29 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:175: T__69
                {
                mT__69(); 


                }
                break;
            case 30 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:181: T__70
                {
                mT__70(); 


                }
                break;
            case 31 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:187: T__71
                {
                mT__71(); 


                }
                break;
            case 32 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:193: T__72
                {
                mT__72(); 


                }
                break;
            case 33 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:199: T__73
                {
                mT__73(); 


                }
                break;
            case 34 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:205: T__74
                {
                mT__74(); 


                }
                break;
            case 35 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:211: T__75
                {
                mT__75(); 


                }
                break;
            case 36 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:217: T__76
                {
                mT__76(); 


                }
                break;
            case 37 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:223: T__77
                {
                mT__77(); 


                }
                break;
            case 38 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:229: T__78
                {
                mT__78(); 


                }
                break;
            case 39 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:235: T__79
                {
                mT__79(); 


                }
                break;
            case 40 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:241: T__80
                {
                mT__80(); 


                }
                break;
            case 41 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:247: T__81
                {
                mT__81(); 


                }
                break;
            case 42 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:253: T__82
                {
                mT__82(); 


                }
                break;
            case 43 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:259: T__83
                {
                mT__83(); 


                }
                break;
            case 44 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:265: T__84
                {
                mT__84(); 


                }
                break;
            case 45 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:271: T__85
                {
                mT__85(); 


                }
                break;
            case 46 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:277: T__86
                {
                mT__86(); 


                }
                break;
            case 47 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:283: T__87
                {
                mT__87(); 


                }
                break;
            case 48 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:289: T__88
                {
                mT__88(); 


                }
                break;
            case 49 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:295: T__89
                {
                mT__89(); 


                }
                break;
            case 50 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:301: T__90
                {
                mT__90(); 


                }
                break;
            case 51 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:307: T__91
                {
                mT__91(); 


                }
                break;
            case 52 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:313: T__92
                {
                mT__92(); 


                }
                break;
            case 53 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:319: T__93
                {
                mT__93(); 


                }
                break;
            case 54 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:325: T__94
                {
                mT__94(); 


                }
                break;
            case 55 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:331: T__95
                {
                mT__95(); 


                }
                break;
            case 56 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:337: T__96
                {
                mT__96(); 


                }
                break;
            case 57 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:343: T__97
                {
                mT__97(); 


                }
                break;
            case 58 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:349: T__98
                {
                mT__98(); 


                }
                break;
            case 59 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:355: T__99
                {
                mT__99(); 


                }
                break;
            case 60 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:361: T__100
                {
                mT__100(); 


                }
                break;
            case 61 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:368: T__101
                {
                mT__101(); 


                }
                break;
            case 62 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:375: T__102
                {
                mT__102(); 


                }
                break;
            case 63 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:382: T__103
                {
                mT__103(); 


                }
                break;
            case 64 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:389: T__104
                {
                mT__104(); 


                }
                break;
            case 65 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:396: T__105
                {
                mT__105(); 


                }
                break;
            case 66 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:403: T__106
                {
                mT__106(); 


                }
                break;
            case 67 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:410: T__107
                {
                mT__107(); 


                }
                break;
            case 68 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:417: T__108
                {
                mT__108(); 


                }
                break;
            case 69 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:424: T__109
                {
                mT__109(); 


                }
                break;
            case 70 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:431: T__110
                {
                mT__110(); 


                }
                break;
            case 71 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:438: T__111
                {
                mT__111(); 


                }
                break;
            case 72 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:445: T__112
                {
                mT__112(); 


                }
                break;
            case 73 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:452: T__113
                {
                mT__113(); 


                }
                break;
            case 74 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:459: T__114
                {
                mT__114(); 


                }
                break;
            case 75 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:466: T__115
                {
                mT__115(); 


                }
                break;
            case 76 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:473: T__116
                {
                mT__116(); 


                }
                break;
            case 77 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:480: T__117
                {
                mT__117(); 


                }
                break;
            case 78 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:487: T__118
                {
                mT__118(); 


                }
                break;
            case 79 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:494: T__119
                {
                mT__119(); 


                }
                break;
            case 80 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:501: T__120
                {
                mT__120(); 


                }
                break;
            case 81 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:508: T__121
                {
                mT__121(); 


                }
                break;
            case 82 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:515: T__122
                {
                mT__122(); 


                }
                break;
            case 83 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:522: T__123
                {
                mT__123(); 


                }
                break;
            case 84 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:529: T__124
                {
                mT__124(); 


                }
                break;
            case 85 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:536: T__125
                {
                mT__125(); 


                }
                break;
            case 86 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:543: T__126
                {
                mT__126(); 


                }
                break;
            case 87 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:550: T__127
                {
                mT__127(); 


                }
                break;
            case 88 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:557: T__128
                {
                mT__128(); 


                }
                break;
            case 89 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:564: T__129
                {
                mT__129(); 


                }
                break;
            case 90 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:571: HexLiteral
                {
                mHexLiteral(); 


                }
                break;
            case 91 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:582: DecimalLiteral
                {
                mDecimalLiteral(); 


                }
                break;
            case 92 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:597: OctalLiteral
                {
                mOctalLiteral(); 


                }
                break;
            case 93 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:610: FloatingPointLiteral
                {
                mFloatingPointLiteral(); 


                }
                break;
            case 94 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:631: CharacterLiteral
                {
                mCharacterLiteral(); 


                }
                break;
            case 95 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:648: StringLiteral
                {
                mStringLiteral(); 


                }
                break;
            case 96 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:662: ENUM
                {
                mENUM(); 


                }
                break;
            case 97 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:667: ASSERT
                {
                mASSERT(); 


                }
                break;
            case 98 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:674: Identifier
                {
                mIdentifier(); 


                }
                break;
            case 99 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:685: WS
                {
                mWS(); 


                }
                break;
            case 100 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:688: COMMENT
                {
                mCOMMENT(); 


                }
                break;
            case 101 :
                // E:\\workspace_16_05_10\\Converter\\lib\\Java.g:1:696: LINE_COMMENT
                {
                mLINE_COMMENT(); 


                }
                break;

        }

    }


    protected DFA18 dfa18 = new DFA18(this);
    protected DFA29 dfa29 = new DFA29(this);
    static final String DFA18_eotS =
        "\6\uffff";
    static final String DFA18_eofS =
        "\6\uffff";
    static final String DFA18_minS =
        "\2\56\4\uffff";
    static final String DFA18_maxS =
        "\1\71\1\146\4\uffff";
    static final String DFA18_acceptS =
        "\2\uffff\1\2\1\1\1\3\1\4";
    static final String DFA18_specialS =
        "\6\uffff}>";
    static final String[] DFA18_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\12\uffff\1\5\1\4\1\5\35\uffff\1\5\1\4\1\5",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "978:1: FloatingPointLiteral : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ FloatTypeSuffix );";
        }
    }
    static final String DFA29_eotS =
        "\1\uffff\1\55\1\64\1\67\1\55\1\uffff\1\55\1\uffff\1\55\1\uffff\1"+
        "\102\2\55\1\112\1\114\1\117\2\uffff\1\122\1\uffff\1\125\1\131\5"+
        "\uffff\1\133\11\55\1\uffff\1\161\2\uffff\2\163\4\uffff\4\55\5\uffff"+
        "\4\55\1\177\4\55\2\uffff\6\55\23\uffff\6\55\1\u0095\14\55\5\uffff"+
        "\1\u00a5\1\163\11\55\1\uffff\1\55\1\u00b2\23\55\1\uffff\3\55\1\u00c9"+
        "\2\55\1\u00cc\6\55\1\u00d3\1\55\1\uffff\1\55\1\u00d6\1\55\1\u00d8"+
        "\2\55\1\u00db\1\u00dc\4\55\1\uffff\12\55\1\u00eb\5\55\1\u00f1\5"+
        "\55\1\uffff\1\u00f7\1\55\1\uffff\1\u00f9\1\55\1\u00fb\2\55\1\u00fe"+
        "\1\uffff\1\55\1\u0100\1\uffff\1\u0101\1\uffff\2\55\2\uffff\12\55"+
        "\1\u010e\1\u010f\2\55\1\uffff\4\55\1\u0116\1\uffff\2\55\1\u0119"+
        "\1\u011b\1\u011c\1\uffff\1\55\1\uffff\1\55\1\uffff\1\u0120\1\55"+
        "\1\uffff\1\u0122\2\uffff\3\55\1\u0126\5\55\1\u012c\1\u012d\1\55"+
        "\2\uffff\1\u012f\3\55\1\u0133\1\55\1\uffff\1\55\1\u0136\1\uffff"+
        "\1\55\2\uffff\1\u0138\1\u0139\1\u013a\1\uffff\1\55\1\uffff\1\55"+
        "\1\u013d\1\55\1\uffff\2\55\1\u0141\1\u0142\1\55\2\uffff\1\55\1\uffff"+
        "\3\55\1\uffff\1\u0148\1\u0149\1\uffff\1\u014a\3\uffff\1\55\1\u014c"+
        "\1\uffff\3\55\2\uffff\1\55\1\u0151\1\55\1\u0153\1\u0154\3\uffff"+
        "\1\55\1\uffff\2\55\1\u0158\1\u0159\1\uffff\1\55\2\uffff\1\u015b"+
        "\1\u015c\1\u015d\2\uffff\1\55\3\uffff\1\55\1\u0160\1\uffff";
    static final String DFA29_eofS =
        "\u0161\uffff";
    static final String DFA29_minS =
        "\1\11\1\141\1\56\1\75\1\154\1\uffff\1\146\1\uffff\1\141\1\uffff"+
        "\1\75\1\150\1\157\2\75\1\46\2\uffff\1\53\1\uffff\1\55\1\52\5\uffff"+
        "\1\75\1\142\1\157\1\145\1\141\1\157\1\141\1\145\2\150\1\uffff\1"+
        "\75\2\uffff\2\56\4\uffff\1\141\1\163\1\141\1\156\5\uffff\1\164\1"+
        "\163\1\165\1\160\1\44\1\163\1\143\1\151\1\142\2\uffff\1\141\1\160"+
        "\1\157\1\151\1\156\1\151\23\uffff\2\163\1\157\1\145\1\164\1\146"+
        "\1\44\1\154\1\156\1\157\1\162\1\156\1\164\1\167\1\154\1\164\1\151"+
        "\1\141\1\151\5\uffff\2\56\1\163\1\145\1\143\1\162\1\164\2\145\1"+
        "\155\1\154\1\uffff\1\164\1\44\1\153\1\166\1\164\1\154\1\164\1\151"+
        "\1\145\1\162\1\164\1\143\1\144\1\141\1\164\1\145\1\154\1\141\1\145"+
        "\1\141\1\142\1\uffff\1\163\2\141\1\44\1\147\1\151\1\44\1\154\1\165"+
        "\1\163\1\157\1\156\1\145\1\44\1\154\1\uffff\1\163\1\44\1\150\1\44"+
        "\1\151\1\156\2\44\1\145\1\162\1\141\1\162\1\uffff\2\141\1\145\2"+
        "\151\1\143\1\162\1\164\1\143\1\150\1\44\1\164\2\162\1\145\1\153"+
        "\1\44\1\165\1\154\1\145\1\154\1\164\1\uffff\1\44\1\166\1\uffff\1"+
        "\44\1\162\1\44\1\167\1\163\1\44\1\uffff\1\145\1\44\1\uffff\1\44"+
        "\1\uffff\1\156\1\144\2\uffff\1\155\1\164\1\156\1\146\1\147\1\164"+
        "\3\143\1\164\2\44\1\150\1\162\1\uffff\1\151\1\141\1\164\1\141\1"+
        "\44\1\uffff\1\154\1\145\3\44\1\uffff\1\145\1\uffff\1\156\1\uffff"+
        "\1\44\1\151\1\uffff\1\44\2\uffff\1\165\1\163\1\145\1\44\1\143\1"+
        "\141\2\145\1\164\2\44\1\146\2\uffff\1\44\1\157\1\154\1\143\1\44"+
        "\1\156\1\uffff\1\164\1\44\1\uffff\1\171\2\uffff\3\44\1\uffff\1\145"+
        "\1\uffff\1\145\1\44\1\156\1\uffff\1\145\1\143\2\44\1\145\2\uffff"+
        "\1\160\1\uffff\1\156\1\145\1\164\1\uffff\2\44\1\uffff\1\44\3\uffff"+
        "\1\156\1\44\1\uffff\1\164\1\157\1\145\2\uffff\1\144\1\44\1\151\2"+
        "\44\3\uffff\1\164\1\uffff\1\163\1\146\2\44\1\uffff\1\172\2\uffff"+
        "\3\44\2\uffff\1\145\3\uffff\1\144\1\44\1\uffff";
    static final String DFA29_maxS =
        "\1\ufaff\1\157\1\71\1\75\1\170\1\uffff\1\156\1\uffff\1\165\1\uffff"+
        "\1\75\1\171\1\157\3\75\2\uffff\1\75\1\uffff\2\75\5\uffff\1\75\1"+
        "\163\1\171\3\157\1\165\1\145\1\162\1\150\1\uffff\1\174\2\uffff\1"+
        "\170\1\146\4\uffff\1\141\1\164\1\141\1\156\5\uffff\1\164\1\163\1"+
        "\165\1\160\1\ufaff\1\164\1\143\1\157\1\142\2\uffff\1\162\1\160\1"+
        "\157\1\151\1\156\1\154\23\uffff\2\163\1\157\1\145\1\164\1\146\1"+
        "\ufaff\1\154\1\156\1\157\1\162\1\156\1\164\1\167\1\154\1\164\1\162"+
        "\1\171\1\151\5\uffff\2\146\1\163\1\145\1\143\1\162\1\164\2\145\1"+
        "\155\1\157\1\uffff\1\164\1\ufaff\1\153\1\166\1\164\1\154\1\164\1"+
        "\151\1\145\1\162\1\164\1\143\1\144\1\141\1\164\1\145\1\154\1\141"+
        "\1\145\1\141\1\142\1\uffff\1\163\2\141\1\ufaff\1\147\1\151\1\ufaff"+
        "\1\154\1\165\1\163\1\157\1\156\1\145\1\ufaff\1\154\1\uffff\1\163"+
        "\1\ufaff\1\150\1\ufaff\1\151\1\156\2\ufaff\1\145\1\162\1\141\1\162"+
        "\1\uffff\2\141\1\145\2\151\1\143\1\162\1\164\1\143\1\150\1\ufaff"+
        "\1\164\2\162\1\145\1\153\1\ufaff\1\165\1\154\1\145\1\154\1\164\1"+
        "\uffff\1\ufaff\1\166\1\uffff\1\ufaff\1\162\1\ufaff\1\167\1\163\1"+
        "\ufaff\1\uffff\1\145\1\ufaff\1\uffff\1\ufaff\1\uffff\1\156\1\144"+
        "\2\uffff\1\155\1\164\1\156\1\146\1\147\1\164\3\143\1\164\2\ufaff"+
        "\1\150\1\162\1\uffff\1\151\1\141\1\164\1\141\1\ufaff\1\uffff\1\154"+
        "\1\145\3\ufaff\1\uffff\1\145\1\uffff\1\156\1\uffff\1\ufaff\1\151"+
        "\1\uffff\1\ufaff\2\uffff\1\165\1\163\1\145\1\ufaff\1\143\1\141\2"+
        "\145\1\164\2\ufaff\1\146\2\uffff\1\ufaff\1\157\1\154\1\143\1\ufaff"+
        "\1\156\1\uffff\1\164\1\ufaff\1\uffff\1\171\2\uffff\3\ufaff\1\uffff"+
        "\1\145\1\uffff\1\145\1\ufaff\1\156\1\uffff\1\145\1\143\2\ufaff\1"+
        "\145\2\uffff\1\160\1\uffff\1\156\1\145\1\164\1\uffff\2\ufaff\1\uffff"+
        "\1\ufaff\3\uffff\1\156\1\ufaff\1\uffff\1\164\1\157\1\145\2\uffff"+
        "\1\144\1\ufaff\1\151\2\ufaff\3\uffff\1\164\1\uffff\1\163\1\146\2"+
        "\ufaff\1\uffff\1\172\2\uffff\3\ufaff\2\uffff\1\145\3\uffff\1\144"+
        "\1\ufaff\1\uffff";
    static final String DFA29_acceptS =
        "\5\uffff\1\5\1\uffff\1\10\1\uffff\1\12\6\uffff\1\26\1\27\1\uffff"+
        "\1\34\2\uffff\1\43\1\45\1\46\1\47\1\50\12\uffff\1\124\1\uffff\1"+
        "\130\1\131\2\uffff\1\136\1\137\1\142\1\143\4\uffff\1\40\1\2\1\135"+
        "\1\44\1\3\11\uffff\1\30\1\13\6\uffff\1\20\1\17\1\22\1\21\1\23\1"+
        "\25\1\24\1\32\1\33\1\31\1\36\1\37\1\35\1\42\1\144\1\145\1\41\1\52"+
        "\1\51\23\uffff\1\126\1\127\1\125\1\132\1\133\13\uffff\1\74\25\uffff"+
        "\1\64\17\uffff\1\134\14\uffff\1\76\26\uffff\1\73\2\uffff\1\102\6"+
        "\uffff\1\121\2\uffff\1\57\1\uffff\1\61\2\uffff\1\66\1\140\16\uffff"+
        "\1\16\5\uffff\1\56\5\uffff\1\100\1\uffff\1\103\1\uffff\1\114\2\uffff"+
        "\1\120\1\uffff\1\1\1\60\14\uffff\1\15\1\110\6\uffff\1\55\2\uffff"+
        "\1\67\1\uffff\1\70\1\72\3\uffff\1\115\1\uffff\1\123\3\uffff\1\7"+
        "\5\uffff\1\106\1\14\1\uffff\1\112\3\uffff\1\141\2\uffff\1\65\1\uffff"+
        "\1\101\1\107\1\116\2\uffff\1\4\3\uffff\1\11\1\104\5\uffff\1\54\1"+
        "\63\1\71\1\uffff\1\62\4\uffff\1\111\1\uffff\1\122\1\53\3\uffff\1"+
        "\77\1\105\1\uffff\1\117\1\6\1\75\2\uffff\1\113";
    static final String DFA29_specialS =
        "\u0161\uffff}>";
    static final String[] DFA29_transitionS = {
            "\2\56\1\uffff\2\56\22\uffff\1\56\1\15\1\54\1\uffff\1\55\1\16"+
            "\1\17\1\53\1\20\1\21\1\12\1\22\1\23\1\24\1\2\1\25\1\51\11\52"+
            "\1\26\1\11\1\7\1\3\1\5\1\27\1\30\32\55\1\31\1\uffff\1\32\1\33"+
            "\1\55\1\uffff\1\34\1\35\1\1\1\36\1\4\1\37\2\55\1\6\2\55\1\40"+
            "\1\55\1\41\1\55\1\10\1\55\1\42\1\13\1\43\1\55\1\14\1\44\3\55"+
            "\1\45\1\46\1\47\1\50\101\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\60\6\uffff\1\61\3\uffff\1\57\2\uffff\1\62",
            "\1\63\1\uffff\12\65",
            "\1\66",
            "\1\71\1\uffff\1\72\11\uffff\1\70",
            "",
            "\1\74\6\uffff\1\73\1\75",
            "",
            "\1\76\20\uffff\1\77\2\uffff\1\100",
            "",
            "\1\101",
            "\1\105\13\uffff\1\103\1\104\1\uffff\1\106\1\uffff\1\107",
            "\1\110",
            "\1\111",
            "\1\113",
            "\1\115\26\uffff\1\116",
            "",
            "",
            "\1\120\21\uffff\1\121",
            "",
            "\1\123\17\uffff\1\124",
            "\1\127\4\uffff\1\130\15\uffff\1\126",
            "",
            "",
            "",
            "",
            "",
            "\1\132",
            "\1\134\20\uffff\1\135",
            "\1\136\2\uffff\1\137\6\uffff\1\140",
            "\1\141\11\uffff\1\142",
            "\1\143\7\uffff\1\144\2\uffff\1\145\2\uffff\1\146",
            "\1\147",
            "\1\150\3\uffff\1\151\17\uffff\1\152",
            "\1\153",
            "\1\154\11\uffff\1\155",
            "\1\156",
            "",
            "\1\157\76\uffff\1\160",
            "",
            "",
            "\1\65\1\uffff\10\164\2\65\12\uffff\3\65\21\uffff\1\162\13\uffff"+
            "\3\65\21\uffff\1\162",
            "\1\65\1\uffff\12\165\12\uffff\3\65\35\uffff\3\65",
            "",
            "",
            "",
            "",
            "\1\166",
            "\1\167\1\170",
            "\1\171",
            "\1\172",
            "",
            "",
            "",
            "",
            "",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0080\1\u0081",
            "\1\u0082",
            "\1\u0083\5\uffff\1\u0084",
            "\1\u0085",
            "",
            "",
            "\1\u0086\20\uffff\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c\2\uffff\1\u008d",
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
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\24"+
            "\55\1\u0094\5\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f\10\uffff\1\u00a0",
            "\1\u00a1\23\uffff\1\u00a2\3\uffff\1\u00a3",
            "\1\u00a4",
            "",
            "",
            "",
            "",
            "",
            "\1\65\1\uffff\10\164\2\65\12\uffff\3\65\35\uffff\3\65",
            "\1\65\1\uffff\12\165\12\uffff\3\65\35\uffff\3\65",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae\2\uffff\1\u00af",
            "",
            "\1\u00b0",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\4\55"+
            "\1\u00b1\25\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55"+
            "\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00ca",
            "\1\u00cb",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00d4",
            "",
            "\1\u00d5",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00d7",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00d9",
            "\1\u00da",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00ec",
            "\1\u00ed",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00f2",
            "\1\u00f3",
            "\1\u00f4",
            "\1\u00f5",
            "\1\u00f6",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00f8",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00fa",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u00fc",
            "\1\u00fd",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u00ff",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u0102",
            "\1\u0103",
            "",
            "",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "\1\u0108",
            "\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0110",
            "\1\u0111",
            "",
            "\1\u0112",
            "\1\u0113",
            "\1\u0114",
            "\1\u0115",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u0117",
            "\1\u0118",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\13"+
            "\55\1\u011a\16\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u011d",
            "",
            "\1\u011e",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\22"+
            "\55\1\u011f\7\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08"+
            "\55\u1040\uffff\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e"+
            "\55\u10d2\uffff\u5200\55\u5900\uffff\u0200\55",
            "\1\u0121",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "\1\u0123",
            "\1\u0124",
            "\1\u0125",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0127",
            "\1\u0128",
            "\1\u0129",
            "\1\u012a",
            "\1\u012b",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u012e",
            "",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0130",
            "\1\u0131",
            "\1\u0132",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0134",
            "",
            "\1\u0135",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u0137",
            "",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u013b",
            "",
            "\1\u013c",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u013e",
            "",
            "\1\u013f",
            "\1\u0140",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0143",
            "",
            "",
            "\1\u0144",
            "",
            "\1\u0145",
            "\1\u0146",
            "\1\u0147",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "",
            "\1\u014b",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u014d",
            "\1\u014e",
            "\1\u014f",
            "",
            "",
            "\1\u0150",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\u0152",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "",
            "\1\u0155",
            "",
            "\1\u0156",
            "\1\u0157",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "\1\u015a",
            "",
            "",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            "",
            "",
            "\1\u015e",
            "",
            "",
            "",
            "\1\u015f",
            "\1\55\13\uffff\12\55\7\uffff\32\55\4\uffff\1\55\1\uffff\32"+
            "\55\105\uffff\27\55\1\uffff\37\55\1\uffff\u1f08\55\u1040\uffff"+
            "\u0150\55\u0170\uffff\u0080\55\u0080\uffff\u092e\55\u10d2\uffff"+
            "\u5200\55\u5900\uffff\u0200\55",
            ""
    };

    static final short[] DFA29_eot = DFA.unpackEncodedString(DFA29_eotS);
    static final short[] DFA29_eof = DFA.unpackEncodedString(DFA29_eofS);
    static final char[] DFA29_min = DFA.unpackEncodedStringToUnsignedChars(DFA29_minS);
    static final char[] DFA29_max = DFA.unpackEncodedStringToUnsignedChars(DFA29_maxS);
    static final short[] DFA29_accept = DFA.unpackEncodedString(DFA29_acceptS);
    static final short[] DFA29_special = DFA.unpackEncodedString(DFA29_specialS);
    static final short[][] DFA29_transition;

    static {
        int numStates = DFA29_transitionS.length;
        DFA29_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA29_transition[i] = DFA.unpackEncodedString(DFA29_transitionS[i]);
        }
    }

    class DFA29 extends DFA {

        public DFA29(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 29;
            this.eot = DFA29_eot;
            this.eof = DFA29_eof;
            this.min = DFA29_min;
            this.max = DFA29_max;
            this.accept = DFA29_accept;
            this.special = DFA29_special;
            this.transition = DFA29_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( CLASS | DOT | EQ | EXTENDS | GT | IMPLEMENTS | IMPORT | LS | PACKAGE | SEMI | STAR | STATIC | SUPER | VOID | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | T__117 | T__118 | T__119 | T__120 | T__121 | T__122 | T__123 | T__124 | T__125 | T__126 | T__127 | T__128 | T__129 | HexLiteral | DecimalLiteral | OctalLiteral | FloatingPointLiteral | CharacterLiteral | StringLiteral | ENUM | ASSERT | Identifier | WS | COMMENT | LINE_COMMENT );";
        }
    }
 

}