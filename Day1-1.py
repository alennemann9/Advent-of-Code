if __name__ == "__main__":

    with open("Day1.txt") as problem:
        inputList = problem.readlines()
    rightList = []
    leftList = []
    total = 0
    for lines in inputList:
        sides = lines.split("   ")
        rightList.append(int(sides[1]))
        leftList.append(int(sides[0]))
    while len(rightList) > 0:
        total += abs(min(rightList) - min(leftList))
        rightList.remove(min(rightList))
        leftList.remove(min(leftList))
    print(total)
