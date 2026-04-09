import sys

def read_file(name):
    with open(name, 'r', encoding='utf-8') as f:
        return f.read()

def write_file(name, data):
    with open(name, 'w', encoding='utf-8') as f:
        f.write(data)

# I can just write python and cpp files with the comments manually using standard strings.
