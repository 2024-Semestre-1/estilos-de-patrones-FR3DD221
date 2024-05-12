// Interfaz Observable que define los métodos para agregar, eliminar y notificar observadores
interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Interfaz Observer que define el método update() para que los observadores reciban actualizaciones
interface Observer {
    void update();
}

// Clase Observable concreta que implementa la interfaz Observable
class Subject implements Observable {
    private int state;
    private List<Observer> observers = new ArrayList<>();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// Clase Observer concreta que implementa la interfaz Observer
class ConcreteObserver implements Observer {
    private final Subject subject;

    public ConcreteObserver(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Estado actualizado: " + subject.getState());
    }
}

// Ejemplo de uso del patrón Observer
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();

        // Creamos dos observadores
        ConcreteObserver observer1 = new ConcreteObserver(subject);
        ConcreteObserver observer2 = new ConcreteObserver(subject);

        // Cambiamos el estado del sujeto
        subject.setState(5);
        subject.setState(10);

        // Removemos un observador
        subject.removeObserver(observer2);

        // Cambiamos el estado del sujeto nuevamente
        subject.setState(15);
    }
}