/**
 * <copyright>
 * </copyright>
 *

 */
package at.bestsolution.efxclipse.tooling.svgpath.svgPath;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>smooth quadratic bezier curveto argument sequence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link at.bestsolution.efxclipse.tooling.svgpath.svgPath.smooth_quadratic_bezier_curveto_argument_sequence#getCoordinate_pairs <em>Coordinate pairs</em>}</li>
 * </ul>
 * </p>
 *
 * @see at.bestsolution.efxclipse.tooling.svgpath.svgPath.SvgPathPackage#getsmooth_quadratic_bezier_curveto_argument_sequence()
 * @model
 * @generated
 */
public interface smooth_quadratic_bezier_curveto_argument_sequence extends EObject
{
  /**
   * Returns the value of the '<em><b>Coordinate pairs</b></em>' containment reference list.
   * The list contents are of type {@link at.bestsolution.efxclipse.tooling.svgpath.svgPath.coordinate_pair}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Coordinate pairs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Coordinate pairs</em>' containment reference list.
   * @see at.bestsolution.efxclipse.tooling.svgpath.svgPath.SvgPathPackage#getsmooth_quadratic_bezier_curveto_argument_sequence_Coordinate_pairs()
   * @model containment="true"
   * @generated
   */
  EList<coordinate_pair> getCoordinate_pairs();

} // smooth_quadratic_bezier_curveto_argument_sequence
