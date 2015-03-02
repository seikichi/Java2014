package ch21.ex21_03;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class WeakValueMapTest {
    public static class 空の場合  {
        WeakValueMap<String, Object> sut;
        
        @Before
        public void setUp() throws Exception {
            sut = new WeakValueMap<>();
        }
        
        @Test
        public void sizeが0を返すこと() throws Exception {
            assertThat(sut.size(), is(0));
        }

        @Test
        public void isEmptyがtrueを返すこと() throws Exception {
            assertThat(sut.isEmpty(), is(true));
        }

        @Test
        public void containsKey_Aがfalseを返すこと() throws Exception {
            assertThat(sut.containsKey("A"), is(false));
        }

        @Test
        public void containsValue_Aがfalseを返すこと() throws Exception {
            assertThat(sut.containsValue("A"), is(false));
        }

        @Test
        public void get_Aはnullを返すこと() throws Exception {
            assertThat(sut.get("A"), is(nullValue()));
        }

        @Test
        public void keySetは空のコレクションを返すこと() throws Exception {
            assertThat(sut.keySet().size(), is(0));
        }

        @Test
        public void valuesは空のコレクションを返すこと() throws Exception {
            assertThat(sut.values().size(), is(0));
        }

        @Test
        public void entrySetは空のコレクションを返すこと() throws Exception {
            assertThat(sut.entrySet().size(), is(0));
        }
    }

    public static class 要素を二つ含むが片方の参照が切れている場合 {
        WeakValueMap<String, Object> sut;
        final String key = "A";
        final String staleKey = "B";
        Object value;

        @Before
        public void setUp() throws Exception {
            sut = new WeakValueMap<>();
            value = new Object();
            
            sut.put(key, value);
            sut.put(staleKey, new Object());

            System.gc();
            Thread.sleep(1);
        }
        
        @Test
        public void sizeが1を返すこと() throws Exception {
            assertThat(sut.size(), is(1));
        }

        @Test
        public void 全ての値の参照が切れた場合サイズが0になること() throws Exception {
            value = null;

            System.gc();
            Thread.sleep(1);
            
            assertThat(sut.size(), is(0));
        }

        @Test
        public void isEmptyはfalseを返すこと() throws Exception {
            assertThat(sut.isEmpty(), is(false));
        }

        @Test
        public void 参照が残っている値のキーに対してcontainsKeyがtrueを返すこと() throws Exception {
            assertThat(sut.containsKey(key), is(true));
        }

        @Test
        public void 参照が残っていない値のキーに対してもcontainsKeyがtrueを返すこと() throws Exception {
            assertThat(sut.containsKey(staleKey), is(true));
        }
        
        @Test
        public void getで値を取得できること() throws Exception {
            assertThat(sut.get(key), is(value));
        }

        @Test
        public void 参照切れの値に対してはgetがnullを返すこと() throws Exception {
            assertThat(sut.get(staleKey), is(nullValue()));
        }

        @Test
        public void removeでエントリーの削除ができること() throws Exception {
            sut.remove(key);
            assertThat(sut.size(), is(0));
            assertThat(sut.get(key), is(nullValue()));
        }

        @Test
        public void removeでエントリーの削除ができること_参照切れの値の場合() throws Exception {
            sut.remove(staleKey);
            assertThat(sut.size(), is(1));
        }

        @Test
        public void clearは要素を全て削除すること() throws Exception {
            sut.clear();
            assertThat(sut.size(), is(0));
        }

        @Test
        public void keySetは参照が切れているキーをも含むコレクションを返すこと() throws Exception {
            assertThat(sut.keySet().size(), is(2));
            assertThat(sut.keySet().contains(key), is(true));
            assertThat(sut.keySet().contains(staleKey), is(true));
        }

        @Test
        public void valuesは参照が切れている値を含まないこと() throws Exception {
            assertThat(sut.values().size(), is(1));
            assertThat(sut.values().contains(value), is(true));
        }

        @Test
        public void entrySetは参照が切れている値を含まないこと() throws Exception {
            assertThat(sut.entrySet().size(), is(1));
        }

        @Test
        public void valuesのイテレート中は値が生存すること() throws Exception {
            Collection<Object> coll = sut.values();

            value = null;
            System.gc();
            Thread.sleep(1);

            assertThat(sut.size(), is(1));
            assertThat(coll.toArray()[0], is(notNullValue()));
        }

        @Test
        public void entrySetのイテレート中は値が生存すること() throws Exception {
            Set<Map.Entry<String, Object>> entrySet = sut.entrySet();

            value = null;
            System.gc();
            Thread.sleep(1);

            assertThat(sut.size(), is(1));
            assertThat(entrySet.toArray(new Map.Entry[entrySet.size()])[0].getValue(), is(notNullValue()));
        }
    }
}
