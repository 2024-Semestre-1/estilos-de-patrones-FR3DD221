import java.util.ArrayList;
import java.util.List;

// Clase que representa un estado guardado del editor
class EditorState {
    private final String content;

    public EditorState(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Clase Editor que tiene un contenido y puede guardar/restaurar estados
class Editor {
    private String content = "";
    
    public void type(String words) {
        content += words;
    }

    public String getContent() {
        return content;
    }

    public EditorState save() {
        return new EditorState(content);
    }

    public void restore(EditorState state) {
        content = state.getContent();
    }
}

// Clase que mantiene un historial de estados del editor
class History {
    private final List<EditorState> states = new ArrayList<>();

    public void push(EditorState state) {
        states.add(state);
    }

    public EditorState undo() {
        int lastIndex = states.size() - 1;
        EditorState lastState = states.get(lastIndex);
        return lastState;
    }

    public EditorState redo() {
        int lastIndex = states.size() + 1;
        EditorState lastState = states.get(lastIndex);
        return lastState;
    }
}

// Uso del patrón Memento
public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();
        History history = new History();

        editor.type("Este es el primer contenido. ");
        history.push(editor.save());

        editor.type("Este es el segundo contenido. ");
        history.push(editor.save());

        editor.type("Este es el tercer contenido. ");
        System.out.println("Contenido actual: " + editor.getContent());

        // Restaurar a una versión anterior
        editor.restore(history.undo());
        System.out.println("Contenido restaurado: " + editor.getContent());

        editor.restore(history.redo());
        System.out.println("Contenido restaurado: " + editor.getContent());
    }
}