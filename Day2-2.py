def increase(order):
    for index in range(1, len(order)):
        if order[index] <= order[index-1]:
            return False
        if order[index] == order[index-1]:
            return False
    return True


def decrease(order):
    for index in range(1, len(order)):
        if order[index] == order[index - 1]:  # Check for repeats
            return False
        if order[index] >= order[index - 1]:  # Ensure strictly decreasing
            return False
    return True


def gap(order):
    for index in range(1, len(order)):
        if abs(order[index] - order[index-1]) > 3:
            index1 = order[index]
            index2 = order[index-1]
            differ = abs(index1-index2)
            return False
    return True


if __name__ == "__main__":
    with open("input2.txt") as problem:
        inputList = problem.readlines()
    safe = 0
    for lines in inputList:
        order = list(map(int, lines.split(" ")))
        if (increase(order) or decrease(order)) and gap(order):
            safe += 1
    print(safe)
