package at.bestsolution.efxclipse.text.editor;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;

/**
 * Interface for a <code>IStorage</code> input to an editor.
 * <p>
 * Clients implementing this editor input interface should override
 * <code>Object.equals(Object)</code> to answer true for two inputs
 * that are the same. The <code>IWorbenchPage.openEditor</code> APIs
 * are dependent on this to find an editor with the same input.
 * </p><p>
 * Clients should implement this interface to declare new types of 
 * <code>IStorage</code> editor inputs.
 * </p><p>
 * File-oriented editors should support this as a valid input type, and display
 * its content for viewing (but not allow modification).
 * Within the editor, the "save" and "save as" operations should create a new 
 * file resource within the workspace.
 * </p><p>
 * All editor inputs must implement the <code>IAdaptable</code> interface;
 * extensions are managed by the platform's adapter manager.
 * </p>
 */
public interface IStorageEditorInput extends IEditorInput {
    /**
     * Returns the underlying IStorage object.
     *
     * @return an IStorage object.
     * @exception CoreException if this method fails
     */
    public IStorage getStorage() throws CoreException;
}
