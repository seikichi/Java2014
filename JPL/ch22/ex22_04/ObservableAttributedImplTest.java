package ch22.ex22_04;

import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.Observable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ObservableAttributedImplTest {
    @Test
    public void addの呼び出しでObserverのupdateが呼ばれること() throws Exception {
        ObservableAttributedImpl<Integer> sut = new ObservableAttributedImpl<>();

        final boolean[] updated = {false};
        sut.addObserver((Observable o, Object arg) -> updated[0] = true);
        sut.add(new Attr<Integer>("birth", 1989));
        assertThat(updated[0], is(true));
    }
}