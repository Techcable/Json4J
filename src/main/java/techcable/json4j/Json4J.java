package techcable.json4j;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Arrays;

public class Json4J {
    
    public static class JsonParser {
        public readValue(JsonStream stream) {
            if (stream.getCurrent() == '{') {
                readObject(stream);
            } else if (stream.getCurrent() == '"') {
                readString(stream);
            }
        }
        
        public void skipWhitespace(JsonStream stream) {
            while (Character.isWhitespace(stream.getCurrentChar())) {
                stream.next();
            }
        }
        
        public JsonObject readString(JsonStream stream) {
            if (stream.getCurrent() != '"') throw new UnexpectedToxenException(stream);
            stream.next();
            while (stream.getCurrent() != '"' && stream.getCharAt(stream.getPosition() - 1) != '\\') {
                char c = stream.getCurrent();
                if (c == '\\' && stream.getCharAt(stream.getPosition() - 1) != '\\') { //Ignore control char
                    c.next();
                    continue;
                }
                if (stream.get
            }
        }
        
        public JsonObject readObject(JsonStream stream) {
            if (stream.getCurrent() != '{') throw new UnexpectedToxenException(stream);
            stream.next();
            while (stream.getCurrent() != '}') {
                String key;
                JsonValue value;
                skipWhitespace(stream);
                if (stream.getCurrent() == '"') {
                    key = readString(stream);
                } else throw new UnexpectedTokenException(stream);
                skipWhitespace(stream);
                if (stream.getCurrent() == ':') {
                    stream.next();
                    skipWhitespace(stream);
                    value = readValue(stream);
                } else throw UnexpectedTokenException(stream);
                skipWhitespace(stream);
                if (stream.getCurrent() == ",") stream.next();
            }
            if (stream.getCurrent() != '{') throw new UnexpectedToxenException(stream);
            stream.next();
        }
    }
    
    public static class JsonStream {
        private char[] reed = new char[0]; //Pronounced red
        private int position = 0;
        private Reader backing;
        
        public JsonStream(Reader reader) {
            if (!(reader instanceof BufferedReader)) {
                reader = new BufferedReader(reader);
            }
            this.reader = reader;
        }
        
        public JsonStream(String string) {
            this.reed = string.toCharArray();
        }
        
        public char getBefore() {
            return getBefore(1);
        }
        
        public char getBefore(int amount) {
            return getCharAt(getPosition() - amount);
        }
        
        public void next(int amount) {
            position += ammount;
        }
        
        public void next() {
            next(1);
        }
        
        public void before(int amount) {
            next(-amount);
        }
        
        public void before() {
            before(1);
        }
        
        public int getPosition() {
            return position;
        }
        
        public char getCurrent() {
            getCharAt(position);
        }
        
        public Character getCharAt(int index) {
            boolean ableToRead = readTill(index) 
            if (!ableToRead) return null;
            return reed[index];
        }
        
        public boolean readTill(int index) {
            if (index > read.length) {
                try {
                    int numReed = read(index - read.length);
                    
                } catch (Exception ex) {
                    return false;
                }
            }
            return true;
        }
        
        public int read(int amount) throws IOException {
            char[] buffer = new char[amount];
            int numReed;
            if (reader == null) throw new IOException("No Reader");
            for (numReed = 0; numReed < amount; reed++) {
                try {
                    buffer[numReed + 1] = backing.read();
                } catch (IOException ex) {
                    return numReed;
                }
            }
            int oldSize = reed;
            reed = increaseSize(reed, numReed);
            for (int i = 0; i < numReed; i++) {
                reed[oldSize + i] = buffer[i];
            }
            return numReed;
        }
        
        public T[] increaseSize(T[] original, int increase) {
            return Arrays.copyOfRange(original, 0, original.length + increase);
        }
    }
    
}