class Pila:
    def __init__(self):
        self.items = []

    def esta_vacia(self):
        return not self.items

    def push(self, item):
        self.items.append(item)

    def pop(self):
        if self.esta_vacia():
            raise Exception("La pila está vacía.")
        return self.items.pop()

    def peek(self):
        if self.esta_vacia():
            raise Exception("La pila está vacía.")
        return self.items[-1]

def prioridad(operador):
    if operador in ['+', '-']:
        return 1
    if operador in ['*', '/']:
        return 2
    if operador in ['^']:
        return 3
    return 0

def infijo_a_postfijo(expr):
    pila = Pila()
    postfijo = []

    for c in expr:
        if c.isalnum():
            postfijo.append(c)
        elif c == '(':
            pila.push(c)
        elif c == ')':
            while not pila.esta_vacia() and pila.peek() != '(':
                postfijo.append(pila.pop())
            if not pila.esta_vacia() and pila.peek() == '(':
                pila.pop()
        else:
            while not pila.esta_vacia() and prioridad(c) <= prioridad(pila.peek()) and pila.peek() != '(':
                postfijo.append(pila.pop())
            pila.push(c)

    while not pila.esta_vacia():
        postfijo.append(pila.pop())

    return "".join(postfijo)

def infijo_a_prefijo(expr):
    expr_invertida = ""
    for c in expr[::-1]:
        if c == '(':
            expr_invertida += ')'
        elif c == ')':
            expr_invertida += '('
        else:
            expr_invertida += c
    
    postfijo_invertido = infijo_a_postfijo(expr_invertida)
    
    return postfijo_invertido[::-1]

if __name__ == "__main__":
    expresion1 = "A+B-C"
    print(f"Expresión infija: {expresion1}")
    print(f"Expresión postfija: {infijo_a_postfijo(expresion1)}")
    print(f"Expresión prefija: {infijo_a_prefijo(expresion1)}")

    expresion2 = "(A+B)*C"
    print(f"\nExpresión infija: {expresion2}")
    print(f"Expresión postfija: {infijo_a_postfijo(expresion2)}")
    print(f"Expresión prefija: {infijo_a_prefijo(expresion2)}")

    expresion3 = "(A*B)-(C+D)"
    print(f"\nExpresión infija: {expresion3}")
    print(f"Expresión postfija: {infijo_a_postfijo(expresion3)}")
    print(f"Expresión prefija: {infijo_a_prefijo(expresion3)}")