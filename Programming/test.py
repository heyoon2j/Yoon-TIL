a = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90]
print(a[2:8:3])    # [20, 50]

def sortedKey(e):
    return e[0]


def solution(priorities, location):
    answer = 0
    sortedList = []

    for i in range(0, len(priorities)):
        sortedList.append((priorities[i], i + 1))

    print(sortedList)
    sortedList.sort(key=sortedKey,reverse=True)
    print(sortedList)

    for i in sortedList:
        answer += 1
        if i[1] == location:
            return answer

    return len(priorities)


priorities = [2, 1, 3, 2]
location = 1
print(solution(priorities, location))

