package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.latex.LatexCommand;
import org.latexlab.docs.client.content.latex.LatexCommandSet;
import org.latexlab.docs.client.content.latex.SetCharacters;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a LaTeX Character Styles menu with on-demand loading.
 */
public class DynamicLatexCharactersMenu extends DynamicMenuBar {

  protected static DynamicLatexCharactersMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   */
  public static DynamicLatexCharactersMenu get() {
    if (instance == null) {
      instance = new DynamicLatexCharactersMenu();
    }
    return instance;
  }
  
  /**
   * Constructs a latex character styles menu.
   */
  protected DynamicLatexCharactersMenu() {
    super(true);
  }

  /**
   * Asynchronously loads the MenuBar's sub items.
   * 
   * @param callback the callback carrying the sub items
   */
  @Override
  protected void getSubMenu(final AsyncCallback<ExtendedMenuItem[]> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
	      callback.onFailure(reason);
		}
		@Override
		public void onSuccess() {
		  LatexCommandSet set = SetCharacters.get();
		  LatexCommand[] cmds = set.getCommands();
		  ExtendedMenuItem[] items = new ExtendedMenuItem[cmds.length];
		  for (int i=0; i<cmds.length; i++) {
			LatexCommand cmd = cmds[i];
			items[i] = new ExtendedMenuItem(cmd.getIcon(), cmd.getTitle(), new SystemPasteCommand(cmd.getText()));
		  }
		  callback.onSuccess(items);
		}
    });
  }

}
