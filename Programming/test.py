cubes = [1, 4, 9, 16, 125]
print(cubes)     # [1, 4, 9, 16, 125]

# 변경
cubes[3] = 64       # [1, 4, 9, 64, 125]
print(cubes)


a = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90]
a[2:5] = ['a', 'b', 'c']     # [0, 10, 'a', 'b', 'c', 50, 60, 70, 80, 90]
print(a)  # [0, 10, 'a', 'b', 'c', 50, 60, 70, 80, 90]


# 추가
cubes.append(216)   # [1, 4, 9, 64, 125, 216]
print(cubes)

cubes.extend([2, 2221])
print(cubes)

cubes[2:4] = []     # [1, 4, 125, 216]
print(cubes)