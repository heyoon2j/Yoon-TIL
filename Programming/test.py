def localVarFunc(param1, param2):
    a = param2 - param1
    b = param2 + param1
    print(a + b)

localVarFunc(4, 5)      #  10


globalVar = 10              # Global
globalVar2 = 20             # Global

def testFunc2():
    globalVar1 = 100        # Local
    global globalVar2       # Global
    globalVar2 = 30         # Global
    print(globalVar)
    print(globalVar2)
    # print(locals())

print(globalVar)            # 10
print(globalVar2)           # 20
testFunc2()                 # 100 \ 30
print(globalVar)            # 10
print(globalVar2)           # 30