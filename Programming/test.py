"""
def solution(bridge_length, weight, truck_weights):
    answer = 0
    trucksOnBridge = []
    curBridgeWeight = 0

    answer += 1



    #while len(truck_weights) != 0:
    while True:
        answer += 1
        if len(trucksOnBridge) != 0:
            for i in trucksOnBridge:
                i[1] += 1
            if trucksOnBridge[0][1] == weight:
                truck = trucksOnBridge.pop(0)
                curBridgeWeight -= truck[0]

        if (curBridgeWeight + truck_weights[0] <= weight) and len(trucksOnBridge) < bridge_length:
            truck = truck_weights.pop(0)
            curBridgeWeight += truck
            trucksOnBridge.append([truck, 0])
            
        if len(truck_weights) == 0 and len(trucksOnBridge) == 0:
            answer -= 1
            break
        print(curBridgeWeight)
        print(str(trucksOnBridge)+ " / " +str(truck_weights))
        
    return answer


# 2, 10, [7, 4, 5, 6]
# 100, 100 [10]
# 100, 100, [10,10,10,10,10,10,10,10,10,10]
bridge_length, weight, truck_weights = 100, 100, [10,10,10,10,10,10,10,10,10,10]
print(solution(bridge_length, weight, truck_weights))
"""



from collections import deque


def solution(bridge_length, weight, truck_weights):
    answer = 0
    trucksOnBridge = deque()
    totalWetight = 0

    while True:
        answer += 1

        if len(trucksOnBridge) != 0:
            for i in trucksOnBridge:
                i[1] += 1

            if trucksOnBridge[0][1] == bridge_length:
                truckOut = trucksOnBridge.popleft()
                totalWetight -= truckOut[0]


        if len(truck_weights) != 0 and len(trucksOnBridge) < bridge_length:
            if totalWetight + truck_weights[0] <= weight:
                truckIn = truck_weights.pop(0)
                trucksOnBridge.append([truckIn, 0])
                totalWetight += truckIn


        print(trucksOnBridge)

        #if len(trucksOnBridge) == 0 and len(truck_weights) == 0:
        if totalWetight == 0:
            return answer        

# 2, 10, [7, 4, 5, 6]
# 100, 100, [10]
# 100, 100, [10,10,10,10,10,10,10,10,10,10]
bridge_length, weight, truck_weights =2, 10, [7, 4, 5, 6] 
print(solution(bridge_length, weight, truck_weights))