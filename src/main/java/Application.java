import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import com.mascotapp.core.MascotApp;
import com.mascotapp.core.MascotAppFactory;
import com.mascotapp.desktop.view.MascotAppView;

public class Application {
	
	private final MascotApp mascotApp;
    private final MascotAppView mascotAppView;

    public Application(String path) throws FileNotFoundException {
        this.mascotApp = MascotAppFactory.create(path);
        this.mascotAppView = new MascotAppView(this.mascotApp);
    }

    public void init() {
        SwingUtilities.invokeLater(this.mascotAppView::init);
    }
}
