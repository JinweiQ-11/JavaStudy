package com.map;

import java.util.*;

/**
 *
 */
public class Countries {
    public static String[][] DATA = new String[][]{
            {"China", "Beijing"}, {"Japan", "Tokyo"},
            {"Taiwan", "Taibei"}, {"Zhejian", "Hangzhou"},
            {"Hubei", "Wuhan"}, {"Jiangsu", "Nanjing"}
    };

    public static void main(String[] args) {
        //System.out.println(Countries.DATA);
        System.out.println(FlyweightMap.names(2));
    }
}

class FlyweightMap extends AbstractMap<String, String> {
//    public static void main(String[] args) {
//        System.out.println(Countries.DATA);
//    }

    @Override
    public Set<Map.Entry<String, String>> entrySet() {
        return entries;
    }

    private static class Entry implements Map.Entry<String, String> {
        int index = -1;

        public Entry(int index) {
            this.index = index;
        }

        public String getKey() {
            return Countries.DATA[index][0];
        }

        public String getValue() {
            return Countries.DATA[index][1];
        }

        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean equals(Object obj) {
            return Countries.DATA[index][0].equals(obj);
        }

        @Override
        public int hashCode() {
            return Countries.DATA[index][0].hashCode();
        }
    }

    private static class EntrySet extends AbstractSet<Map.Entry<String, String>> {
        private int size;
        private Entry entry = new Entry(-1);

        public EntrySet(int size) {
            if (size < 0) {
                this.size = 0;
            } else if (size > Countries.DATA.length) {
                this.size = Countries.DATA.length;
            } else {
                this.size = size;
            }
        }

        @Override
        public Iterator<Map.Entry<String, String>> iterator() {
            return new Iter();
        }

        @Override
        public int size() {
            return size;
        }

        private class Iter implements Iterator<Map.Entry<String, String>> {

            public boolean hasNext() {
                System.out.println("size: " + size);

                return entry.index < size-1;
            }

            public Map.Entry<String, String> next() {
                entry.index++;
                System.out.println("entry index: " + entry.index);
                return entry;
            }
        }
    }

    private static Set<Map.Entry<String, String>> entries = new EntrySet(Countries.DATA.length);

    public static Map<String, String> select(final int size) {
        return new FlyweightMap() {
            @Override
            public Set<Map.Entry<String, String>> entrySet() {
                return new EntrySet(size);
            }
        };
    }

    public static Map<String, String> map = new FlyweightMap();

    public static Map<String, String> capitals() {
        return map;
    }

    public static Map<String, String> capitals(int size) {
        return select(size);
    }

    static List<String> names = new ArrayList<String>(map.keySet());

    public static List<String> names() {
        return names;
    }

    public static List<String> names(int size) {
        return new ArrayList<String>(select(size).keySet());
    }


}