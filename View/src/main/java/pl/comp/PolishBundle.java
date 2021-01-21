package pl.comp;

import java.util.ListResourceBundle;

public class PolishBundle extends ListResourceBundle {
    static final Object[][] resources = new Object[3][2];

    @Override
    protected Object[][] getContents() {
        resources[0][0] = "madeBy";
        resources[0][1] = "Stworzyli: ";

        resources[1][0] = "author1";
        resources[1][1] = "Szymon Blazynski";

        resources[2][0] = "author2";
        resources[2][1] = "Patryk Makowski";

        return resources;
    }
}
