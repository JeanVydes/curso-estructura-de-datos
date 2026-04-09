def factorial(n):
    if n <= 1:
        return 1
    return n * factorial(n - 1)

def fibonacci(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    return fibonacci(n - 1) + fibonacci(n - 2)

def suma_digitos(n):
    if n < 10:
        return n
    return (n % 10) + suma_digitos(n // 10)

def potencia(base, exp):
    if exp == 0:
        return 1
    return base * potencia(base, exp - 1)

if __name__ == "__main__":
    print("=== APLICACIÓN 1: FACTORIAL ===")
    print(f"Factorial de 4 es: {factorial(4)}")
    print(f"Factorial de 5 es: {factorial(5)}")

    print("\n=== APLICACIÓN 2: FIBONACCI ===")
    print(f"Fibonacci (posición 4) es: {fibonacci(4)}")
    print(f"Fibonacci (posición 6) es: {fibonacci(6)}")
    print("Secuencia de Finobacci hasta la posición 6: ", end="")
    for i in range(7):
        print(f"{fibonacci(i)} ", end="")
    print()

    print("\n=== APLICACIÓN 3: SUMA DE DÍGITOS ===")
    num = 12345
    print(f"Suma de los dígitos de {num} es: {suma_digitos(num)}")

    print("\n=== APLICACIÓN 4: POTENCIA ===")
    print(f"2 elevado a 3 es: {potencia(2, 3)}")
    print(f"5 elevado a 3 es: {potencia(5, 3)}")

