package org.latexlab.docs.client.content.windows;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.latex.LatexCommand;
import org.latexlab.docs.client.content.latex.SetDiverseSymbols;
import org.latexlab.docs.client.widgets.LatexCommandToolbar;
import org.latexlab.docs.client.widgets.WindowManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * A toolbar containing LaTeX commands.
 */
public class ToolbarWindowDiverseSymbols extends LatexCommandToolbar {

  protected static ToolbarWindowDiverseSymbols instance;

  /**
   * Retrieves the single instance of this class.
   * 
   * @param manager the window manager
   */
  public static ToolbarWindowDiverseSymbols get(final WindowManager manager) {
    if (instance == null) {
      instance = new ToolbarWindowDiverseSymbols();
      instance.registeredDragController = manager.getWindowController().getPickupDragController();
      instance.hide();
      manager.getWindowController().makeResizable(instance);
      manager.getBoundaryPanel().add(instance, 500, 120);
    }
    return instance;
  }

  protected ToolbarWindowDiverseSymbols() {
	super(SetDiverseSymbols.TITLE);
  }

  @Override
  protected void getToolbarContents(final AsyncCallback<ToolbarWindowContents> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
		  callback.onFailure(reason);
		}
		@Override
		public void onSuccess() {
		  ToolbarWindowContents contents = new ToolbarWindowContents();
		  SetDiverseSymbols commandSet = SetDiverseSymbols.get();
		  for (LatexCommand cmd : commandSet.getCommands()) {
		    contents.addButton(cmd.getIcon(), cmd.getTitle(), false,
		      new SystemPasteCommand(cmd.getText()));
		  }
		}
    });
  }

}
