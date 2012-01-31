/*
* generated by Xtext
*/
package at.bestsolution.efxclipse.tooling.svgpath.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import at.bestsolution.efxclipse.tooling.svgpath.services.SvgPathGrammarAccess;

public class SvgPathParser extends AbstractContentAssistParser {
	
	@Inject
	private SvgPathGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected at.bestsolution.efxclipse.tooling.svgpath.ui.contentassist.antlr.internal.InternalSvgPathParser createParser() {
		at.bestsolution.efxclipse.tooling.svgpath.ui.contentassist.antlr.internal.InternalSvgPathParser result = new at.bestsolution.efxclipse.tooling.svgpath.ui.contentassist.antlr.internal.InternalSvgPathParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getMovetoAccess().getAlternatives_0(), "rule__Moveto__Alternatives_0");
					put(grammarAccess.getDrawto_commandAccess().getAlternatives(), "rule__Drawto_command__Alternatives");
					put(grammarAccess.getClosepathAccess().getAlternatives_1(), "rule__Closepath__Alternatives_1");
					put(grammarAccess.getLinetoAccess().getAlternatives_0(), "rule__Lineto__Alternatives_0");
					put(grammarAccess.getHorizontal_linetoAccess().getAlternatives_0(), "rule__Horizontal_lineto__Alternatives_0");
					put(grammarAccess.getVertical_linetoAccess().getAlternatives_0(), "rule__Vertical_lineto__Alternatives_0");
					put(grammarAccess.getCurvetoAccess().getAlternatives_0(), "rule__Curveto__Alternatives_0");
					put(grammarAccess.getSmooth_curvetoAccess().getAlternatives_0(), "rule__Smooth_curveto__Alternatives_0");
					put(grammarAccess.getQuadratic_bezier_curvetoAccess().getAlternatives_0(), "rule__Quadratic_bezier_curveto__Alternatives_0");
					put(grammarAccess.getSmooth_quadratic_bezier_curvetoAccess().getAlternatives_0(), "rule__Smooth_quadratic_bezier_curveto__Alternatives_0");
					put(grammarAccess.getElliptical_arcAccess().getAlternatives_0(), "rule__Elliptical_arc__Alternatives_0");
					put(grammarAccess.getExponentAccess().getAlternatives_0(), "rule__Exponent__Alternatives_0");
					put(grammarAccess.getDigitAccess().getAlternatives(), "rule__Digit__Alternatives");
					put(grammarAccess.getSignAccess().getAlternatives(), "rule__Sign__Alternatives");
					put(grammarAccess.getFlagAccess().getAlternatives(), "rule__Flag__Alternatives");
					put(grammarAccess.getMoveto_drawto_command_groupAccess().getGroup(), "rule__Moveto_drawto_command_group__Group__0");
					put(grammarAccess.getMovetoAccess().getGroup(), "rule__Moveto__Group__0");
					put(grammarAccess.getLineto_argument_sequenceAccess().getGroup(), "rule__Lineto_argument_sequence__Group__0");
					put(grammarAccess.getLineto_argument_sequenceAccess().getGroup_1(), "rule__Lineto_argument_sequence__Group_1__0");
					put(grammarAccess.getClosepathAccess().getGroup(), "rule__Closepath__Group__0");
					put(grammarAccess.getLinetoAccess().getGroup(), "rule__Lineto__Group__0");
					put(grammarAccess.getHorizontal_linetoAccess().getGroup(), "rule__Horizontal_lineto__Group__0");
					put(grammarAccess.getHorizontal_lineto_argument_sequenceAccess().getGroup(), "rule__Horizontal_lineto_argument_sequence__Group__0");
					put(grammarAccess.getHorizontal_lineto_argument_sequenceAccess().getGroup_1(), "rule__Horizontal_lineto_argument_sequence__Group_1__0");
					put(grammarAccess.getVertical_linetoAccess().getGroup(), "rule__Vertical_lineto__Group__0");
					put(grammarAccess.getVertical_lineto_argument_sequenceAccess().getGroup(), "rule__Vertical_lineto_argument_sequence__Group__0");
					put(grammarAccess.getVertical_lineto_argument_sequenceAccess().getGroup_1(), "rule__Vertical_lineto_argument_sequence__Group_1__0");
					put(grammarAccess.getCurvetoAccess().getGroup(), "rule__Curveto__Group__0");
					put(grammarAccess.getCurveto_argument_sequenceAccess().getGroup(), "rule__Curveto_argument_sequence__Group__0");
					put(grammarAccess.getCurveto_argument_sequenceAccess().getGroup_1(), "rule__Curveto_argument_sequence__Group_1__0");
					put(grammarAccess.getCurveto_argumentAccess().getGroup(), "rule__Curveto_argument__Group__0");
					put(grammarAccess.getSmooth_curvetoAccess().getGroup(), "rule__Smooth_curveto__Group__0");
					put(grammarAccess.getSmooth_curveto_argument_sequenceAccess().getGroup(), "rule__Smooth_curveto_argument_sequence__Group__0");
					put(grammarAccess.getSmooth_curveto_argument_sequenceAccess().getGroup_1(), "rule__Smooth_curveto_argument_sequence__Group_1__0");
					put(grammarAccess.getSmooth_curveto_argumentAccess().getGroup(), "rule__Smooth_curveto_argument__Group__0");
					put(grammarAccess.getQuadratic_bezier_curvetoAccess().getGroup(), "rule__Quadratic_bezier_curveto__Group__0");
					put(grammarAccess.getQuadratic_bezier_curveto_argument_sequenceAccess().getGroup(), "rule__Quadratic_bezier_curveto_argument_sequence__Group__0");
					put(grammarAccess.getQuadratic_bezier_curveto_argument_sequenceAccess().getGroup_1(), "rule__Quadratic_bezier_curveto_argument_sequence__Group_1__0");
					put(grammarAccess.getQuadratic_bezier_curveto_argumentAccess().getGroup(), "rule__Quadratic_bezier_curveto_argument__Group__0");
					put(grammarAccess.getSmooth_quadratic_bezier_curvetoAccess().getGroup(), "rule__Smooth_quadratic_bezier_curveto__Group__0");
					put(grammarAccess.getSmooth_quadratic_bezier_curveto_argument_sequenceAccess().getGroup(), "rule__Smooth_quadratic_bezier_curveto_argument_sequence__Group__0");
					put(grammarAccess.getSmooth_quadratic_bezier_curveto_argument_sequenceAccess().getGroup_1(), "rule__Smooth_quadratic_bezier_curveto_argument_sequence__Group_1__0");
					put(grammarAccess.getElliptical_arcAccess().getGroup(), "rule__Elliptical_arc__Group__0");
					put(grammarAccess.getElliptical_arc_argument_sequenceAccess().getGroup(), "rule__Elliptical_arc_argument_sequence__Group__0");
					put(grammarAccess.getElliptical_arc_argument_sequenceAccess().getGroup_1(), "rule__Elliptical_arc_argument_sequence__Group_1__0");
					put(grammarAccess.getElliptical_arc_argumentAccess().getGroup(), "rule__Elliptical_arc_argument__Group__0");
					put(grammarAccess.getCoordinate_pairAccess().getGroup(), "rule__Coordinate_pair__Group__0");
					put(grammarAccess.getNumberAccess().getGroup(), "rule__Number__Group__0");
					put(grammarAccess.getNonnegative_numberAccess().getGroup(), "rule__Nonnegative_number__Group__0");
					put(grammarAccess.getNonnegative_numberAccess().getGroup_1(), "rule__Nonnegative_number__Group_1__0");
					put(grammarAccess.getExponentAccess().getGroup(), "rule__Exponent__Group__0");
					put(grammarAccess.getSvg_pathAccess().getMoveto_drawto_command_groupsAssignment(), "rule__Svg_path__Moveto_drawto_command_groupsAssignment");
					put(grammarAccess.getMoveto_drawto_command_groupsAccess().getCommandsAssignment(), "rule__Moveto_drawto_command_groups__CommandsAssignment");
					put(grammarAccess.getMoveto_drawto_command_groupAccess().getMovetoAssignment_0(), "rule__Moveto_drawto_command_group__MovetoAssignment_0");
					put(grammarAccess.getMoveto_drawto_command_groupAccess().getDrawto_commandsAssignment_1(), "rule__Moveto_drawto_command_group__Drawto_commandsAssignment_1");
					put(grammarAccess.getMovetoAccess().getPointAssignment_1(), "rule__Moveto__PointAssignment_1");
					put(grammarAccess.getMovetoAccess().getLineto_argument_sequenceAssignment_3(), "rule__Moveto__Lineto_argument_sequenceAssignment_3");
					put(grammarAccess.getLineto_argument_sequenceAccess().getPairsAssignment_0(), "rule__Lineto_argument_sequence__PairsAssignment_0");
					put(grammarAccess.getLineto_argument_sequenceAccess().getPairsAssignment_1_1(), "rule__Lineto_argument_sequence__PairsAssignment_1_1");
					put(grammarAccess.getDrawto_commandsAccess().getDrawto_commandsAssignment(), "rule__Drawto_commands__Drawto_commandsAssignment");
					put(grammarAccess.getLinetoAccess().getLineto_argument_sequenceAssignment_1(), "rule__Lineto__Lineto_argument_sequenceAssignment_1");
					put(grammarAccess.getHorizontal_linetoAccess().getHorizontal_lineto_argument_sequenceAssignment_1(), "rule__Horizontal_lineto__Horizontal_lineto_argument_sequenceAssignment_1");
					put(grammarAccess.getHorizontal_lineto_argument_sequenceAccess().getCoordinatesAssignment_0(), "rule__Horizontal_lineto_argument_sequence__CoordinatesAssignment_0");
					put(grammarAccess.getHorizontal_lineto_argument_sequenceAccess().getCoordinatesAssignment_1_1(), "rule__Horizontal_lineto_argument_sequence__CoordinatesAssignment_1_1");
					put(grammarAccess.getVertical_linetoAccess().getVertical_lineto_argument_sequenceAssignment_1(), "rule__Vertical_lineto__Vertical_lineto_argument_sequenceAssignment_1");
					put(grammarAccess.getVertical_lineto_argument_sequenceAccess().getCoordinatesAssignment_0(), "rule__Vertical_lineto_argument_sequence__CoordinatesAssignment_0");
					put(grammarAccess.getVertical_lineto_argument_sequenceAccess().getCoordinatesAssignment_1_1(), "rule__Vertical_lineto_argument_sequence__CoordinatesAssignment_1_1");
					put(grammarAccess.getCurvetoAccess().getCurveto_argument_sequenceAssignment_1(), "rule__Curveto__Curveto_argument_sequenceAssignment_1");
					put(grammarAccess.getCurveto_argument_sequenceAccess().getCurveto_argumentsAssignment_0(), "rule__Curveto_argument_sequence__Curveto_argumentsAssignment_0");
					put(grammarAccess.getCurveto_argument_sequenceAccess().getCurveto_argumentsAssignment_1_1(), "rule__Curveto_argument_sequence__Curveto_argumentsAssignment_1_1");
					put(grammarAccess.getCurveto_argumentAccess().getC1Assignment_0(), "rule__Curveto_argument__C1Assignment_0");
					put(grammarAccess.getCurveto_argumentAccess().getC2Assignment_2(), "rule__Curveto_argument__C2Assignment_2");
					put(grammarAccess.getCurveto_argumentAccess().getC3Assignment_4(), "rule__Curveto_argument__C3Assignment_4");
					put(grammarAccess.getSmooth_curvetoAccess().getSmooth_curveto_argument_sequenceAssignment_1(), "rule__Smooth_curveto__Smooth_curveto_argument_sequenceAssignment_1");
					put(grammarAccess.getSmooth_curveto_argument_sequenceAccess().getSmooth_curveto_argumentsAssignment_0(), "rule__Smooth_curveto_argument_sequence__Smooth_curveto_argumentsAssignment_0");
					put(grammarAccess.getSmooth_curveto_argument_sequenceAccess().getSmooth_curveto_argumentsAssignment_1_1(), "rule__Smooth_curveto_argument_sequence__Smooth_curveto_argumentsAssignment_1_1");
					put(grammarAccess.getSmooth_curveto_argumentAccess().getC1Assignment_0(), "rule__Smooth_curveto_argument__C1Assignment_0");
					put(grammarAccess.getSmooth_curveto_argumentAccess().getC2Assignment_2(), "rule__Smooth_curveto_argument__C2Assignment_2");
					put(grammarAccess.getQuadratic_bezier_curveto_argument_sequenceAccess().getQuadratic_bezier_curveto_argumentsAssignment_0(), "rule__Quadratic_bezier_curveto_argument_sequence__Quadratic_bezier_curveto_argumentsAssignment_0");
					put(grammarAccess.getQuadratic_bezier_curveto_argument_sequenceAccess().getQuadratic_bezier_curveto_argumentsAssignment_1_1(), "rule__Quadratic_bezier_curveto_argument_sequence__Quadratic_bezier_curveto_argumentsAssignment_1_1");
					put(grammarAccess.getQuadratic_bezier_curveto_argumentAccess().getC1Assignment_0(), "rule__Quadratic_bezier_curveto_argument__C1Assignment_0");
					put(grammarAccess.getQuadratic_bezier_curveto_argumentAccess().getC2Assignment_2(), "rule__Quadratic_bezier_curveto_argument__C2Assignment_2");
					put(grammarAccess.getSmooth_quadratic_bezier_curvetoAccess().getSmooth_quadratic_bezier_curveto_argument_sequenceAssignment_1(), "rule__Smooth_quadratic_bezier_curveto__Smooth_quadratic_bezier_curveto_argument_sequenceAssignment_1");
					put(grammarAccess.getSmooth_quadratic_bezier_curveto_argument_sequenceAccess().getCoordinate_pairsAssignment_0(), "rule__Smooth_quadratic_bezier_curveto_argument_sequence__Coordinate_pairsAssignment_0");
					put(grammarAccess.getSmooth_quadratic_bezier_curveto_argument_sequenceAccess().getCoordinate_pairsAssignment_1_1(), "rule__Smooth_quadratic_bezier_curveto_argument_sequence__Coordinate_pairsAssignment_1_1");
					put(grammarAccess.getElliptical_arcAccess().getElliptical_arc_argument_sequenceAssignment_1(), "rule__Elliptical_arc__Elliptical_arc_argument_sequenceAssignment_1");
					put(grammarAccess.getElliptical_arc_argument_sequenceAccess().getElliptical_arc_argumentsAssignment_0(), "rule__Elliptical_arc_argument_sequence__Elliptical_arc_argumentsAssignment_0");
					put(grammarAccess.getElliptical_arc_argument_sequenceAccess().getElliptical_arc_argumentsAssignment_1_1(), "rule__Elliptical_arc_argument_sequence__Elliptical_arc_argumentsAssignment_1_1");
					put(grammarAccess.getElliptical_arc_argumentAccess().getRxAssignment_0(), "rule__Elliptical_arc_argument__RxAssignment_0");
					put(grammarAccess.getElliptical_arc_argumentAccess().getRyAssignment_2(), "rule__Elliptical_arc_argument__RyAssignment_2");
					put(grammarAccess.getElliptical_arc_argumentAccess().getRotationAssignment_4(), "rule__Elliptical_arc_argument__RotationAssignment_4");
					put(grammarAccess.getElliptical_arc_argumentAccess().getLargearcflagAssignment_6(), "rule__Elliptical_arc_argument__LargearcflagAssignment_6");
					put(grammarAccess.getElliptical_arc_argumentAccess().getSweepflagAssignment_8(), "rule__Elliptical_arc_argument__SweepflagAssignment_8");
					put(grammarAccess.getElliptical_arc_argumentAccess().getCoordinate_pairAssignment_10(), "rule__Elliptical_arc_argument__Coordinate_pairAssignment_10");
					put(grammarAccess.getCoordinate_pairAccess().getC1Assignment_0(), "rule__Coordinate_pair__C1Assignment_0");
					put(grammarAccess.getCoordinate_pairAccess().getC2Assignment_2(), "rule__Coordinate_pair__C2Assignment_2");
					put(grammarAccess.getNumberAccess().getSignAssignment_0(), "rule__Number__SignAssignment_0");
					put(grammarAccess.getNumberAccess().getNonnegative_numberAssignment_1(), "rule__Number__Nonnegative_numberAssignment_1");
					put(grammarAccess.getNonnegative_numberAccess().getIntseqAssignment_0(), "rule__Nonnegative_number__IntseqAssignment_0");
					put(grammarAccess.getNonnegative_numberAccess().getFloatseqAssignment_1_1(), "rule__Nonnegative_number__FloatseqAssignment_1_1");
					put(grammarAccess.getNonnegative_numberAccess().getExponentAssignment_2(), "rule__Nonnegative_number__ExponentAssignment_2");
					put(grammarAccess.getExponentAccess().getSignAssignment_1(), "rule__Exponent__SignAssignment_1");
					put(grammarAccess.getExponentAccess().getDigit_sequenceAssignment_2(), "rule__Exponent__Digit_sequenceAssignment_2");
					put(grammarAccess.getDigit_sequenceAccess().getDigitsAssignment(), "rule__Digit_sequence__DigitsAssignment");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			at.bestsolution.efxclipse.tooling.svgpath.ui.contentassist.antlr.internal.InternalSvgPathParser typedParser = (at.bestsolution.efxclipse.tooling.svgpath.ui.contentassist.antlr.internal.InternalSvgPathParser) parser;
			typedParser.entryRulesvg_path();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WSP" };
	}
	
	public SvgPathGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(SvgPathGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}