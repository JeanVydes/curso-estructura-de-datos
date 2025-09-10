# Creamos la clase para el nodo
class Nodo:
    def __init__(self, dato=None):
        self.dato = dato
        self.siguiente = None

# Creamos la clase para la pila
class Pila:
    def __init__(self):
        self.cima = None

    # Operacion push: anadir un elemento
    def push(self, dato):
        nuevo_nodo = Nodo(dato)
        nuevo_nodo.siguiente = self.cima
        self.cima = nuevo_nodo
        print(f"Push: {dato}")

    # Operacion pop: eliminar el elemento superior
    def pop(self):
        if self.esta_vacia():
            print("Error: La pila esta vacia.")
            return None
        dato = self.cima.dato
        self.cima = self.cima.siguiente
        print(f"Pop: {dato}")
        return dato

    # Operacion peek: ver el elemento superior sin eliminarlo
    def peek(self):
        if self.esta_vacia():
            return None
        return self.cima.dato

    # Verificar si la pila esta vacia
    def esta_vacia(self):
        return self.cima is None

    # Vacia la pila
    def vaciar(self):
        self.cima = None

    # Verifica si los paréntesis, corchetes y llaves en una expresión están balanceados
    def esta_balanceado(self, expresion):
        self.vaciar()
        for c in expresion:
            if c in "([{":
                self.push(c)
            elif c in ")]}":
                if self.esta_vacia():
                    return False
                ultimo_abierto = self.pop()
                if (c == ')' and ultimo_abierto != '(') or \
                   (c == ']' and ultimo_abierto != '[') or \
                   (c == '}' and ultimo_abierto != '{'):
                    return False
        return self.esta_vacia()

    # Invierte una cadena de texto
    def invertir_cadena(self, expresion):
        self.vaciar()
        pila_temporal = Pila()
        for c in expresion:
            pila_temporal.push(c)
        
        cadena_invertida = ""
        while not pila_temporal.esta_vacia():
            cadena_invertida += pila_temporal.pop()
        
        return cadena_invertida

# --- Uso de las nuevas funciones ---
if __name__ == "__main__":
    pila = Pila()

    # Probar el balanceo de paréntesis
    expresiones = ["{[()]}", "{{{([{}])}}}", "{[}]}", "}[{()}]"]
    print("--- Verificación de balanceo de paréntesis ---")
    for expr in expresiones:
        print(f"'{expr}' está balanceado? {pila.esta_balanceado(expr)}")

    print("\n--- Inversión de cadenas ---")
    original = "Hola Mundo!"
    print(f"Original: '{original}'")
    print(f"Invertida: '{pila.invertir_cadena(original)}'")