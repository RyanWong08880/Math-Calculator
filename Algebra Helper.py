# the python version of this project 

#Solve for x and y, given this system of equations:
# ax + by = c
# dx + ey = f
def systemOfTwoEquationsAndTwoVariables():
  a = input('a: ')
  b = input('b: ')
  c = input('c: ')
  d = input('d: ')
  e = input('e: ')
  f = input('f: ')

  x = (float(b) * float(f) - float(e) * float(c)) / (float(b) * float(d) - float(e) * float(a))

  y = (float(c) - float(a) * float(x)) / float(b)

  print('\n(' + str(x) + ', ' + str(y) + ')\n')

#Given a * x ^ 2 + bx + c = 0, solve for x
def quadraticFormula():
  a = float(input('a: '))
  b = float(input('b: '))
  c = float(input('c: '))

  print()
  print(b * b - 4 * a * c)
  print()
  print((0 - b + (b * b - 4 * a * c) ** .5) / (2 * a))
  print('or')
  print((0 - b - (b * b - 4 * a * c) ** .5) / (2 * a))
  print()

#Given this system of equations, solve for x, y and z
#ax + by + cz = d
#ex + fy + gz = h
#ix + jy + kz = l

def systemOfThreeEquationsAndThreeVariables():
  a = int(input("a: "))
  b = int(input("b: "))
  c = int(input("c: "))
  d = int(input("d: "))
  e = int(input("e: "))
  f = int(input("f: "))
  g = int(input("g: "))
  h = int(input("h: "))
  i = int(input("i: "))
  j = int(input("j: "))
  k = int(input("k: "))
  l = int(input("l: "))

  y = (a * h * i * c + e * d * a * k + a * a * l * g + i * d * e * c - a * a * h * k - e * d * i * c - a * l * e * c - i * d * a * g) / (e * b * a * k - a * a * f * k + a * f * i * c - i * b * a * g + a * a * j * g - a * j * e * c)

  z = (a * h - e * d + e * b * y - a * f * y) / (a * g - e * c)

  x = (d - c * z - b * y) / a

  print('(' + str(x) + ', ' + str(y) + ', ' + str(z) + ')')

#Given y = ax^2 + bx + c, print the vertex form:
#   y = a(x - h)^2 + k
def quadraticStandardToVertexForm():
  a = int(input('a: '))
  b = int(input('b: '))
  c = int(input('c: '))

  n = 0 - b / (2 * a)

  print('y = ' + str(a) + '(x + ' + str(0 - n) + ') ^ 2 + ' + str(a * n ** 2 + b * n + c))

#factor a quadtratic
#wip
def factorQuadratic():
  a = float(input('a: '))
  b = float(input('b: '))
  c = float(input('c: '))

  print(str(a) + 'x ( x + ' + str((0 - b + (b ** 2 - 4 * a * c) ** .5) / (2 * a)) + ' ) + ' + str(c) + ' ( x + ' + str((0 - b + (b ** 2 - 4 * a * c) ** .5) / (2 * a)) + ' )\n\nor\n')
  print('( ' + str(a) + ' x + ' + str(c) + ' ) ( x + ' + str((0 - b + (b ** 2 - 4 * a * c) ** .5) / (2 * a)) + ' )')

# Given a float, x, where 0 < x < 1, print the fraction form of x.
# All repeated floats are rounded to the nearest 16th decimal place.
def decimalToFraction():
  x = float(input('x: '))
  if x >= 1 or x <= 0:
    return
  
  notdone = True
  denominator = 2

  while(notdone):
    for i in range(1, denominator):
      if i / denominator == x:
        print(str(i) + ' / ' + str(denominator))
        notdone = False
        break
    denominator += 1


#dont remember what this does
#wip
def degreeOfPolynomials():
  x = (input('table: ')).split(' ')
  for i in x:
    i = float(i)

  notDone = True
  count = 0

  while(notDone):
    y = []
    
    for i in range(len(x) - 1):
      y[i] = x[i] - x[i + 1]

    z = y[0]
    for i in y:
      if y != z:
        notDone = True
        break
      else:
        notDone = False

    x = y
    count += 1

#Given an equation, ax^3 + bx^2 + cx + d = 0, solve for all 3 real or imaginary solutions (x)
def cubicQuadnomial():
  a = float(input('a: '))
  b = float(input('b: '))
  c = float(input('c: '))
  d = float(input('d: '))

  #1st factor
  Q = []
  for i in range(1, abs(int(a)) + 1):
    if a % i == 0:
      Q.append(i)

  P = []
  for i in range(1, abs(int(d)) + 1):
    if d % i == 0:
      P.append(i)

  syntheticDivisor = 999999999999
  for i in P:
    for j in Q:
      if a * (i / j) ** 3 + b * (i / j) ** 2 + c * (i / j) + d == 0:
        syntheticDivisor = i / j
        break
      if a * (0 - (i / j)) ** 3 + b * (i / j) ** 2 + c * (0 - (i / j)) + d == 0:
        syntheticDivisor = 0 - (i / j)
        break
    
  #synthetic division
  coefficients = [a, b, c, d]
  quotients = []
  temp = 0
  for i in coefficients:
    i = float(i)
    temp += i
    quotients.append(temp)
    temp *= syntheticDivisor

  #quadratic formula
  aa = quotients[0]
  bb = quotients[1]
  cc = quotients[2]

  #print answers
  print()
  print(syntheticDivisor)
  print((0 - bb + (bb * bb - 4 * aa * cc) ** .5) / (2 * aa))
  print((0 - bb - (bb * bb - 4 * aa * cc) ** .5) / (2 * aa))
  print()

#Given (a) in (x - a) and the coefficients of a polynomial separated by a space ( ), print the qoutient and remainder
def syntheticDivision():
  divisor = float(input('divisor: '))
  coefficients = input('coefficients: ').split(' ')
  quotients = []
  temp = 0

  for i in coefficients:
    temp += float(i)
    quotients.append(temp)
    temp *= divisor

  print(quotients)
  print()

#Given all coefficients of an equation, ax^n + bx^(n-1) + ... + zx = 0, solve for all solutions (x)
#wip
def findAllRoots():
  coefficients = input('coefficients: ').split(" ")
  for i in range(0, len(coefficients)):
    coefficients[i] = float(coefficients[i])

  Q = []
  for i in range(1, abs(int(coefficients[0])) + 1):
    if coefficients[0] % i == 0:
      Q.append(i)

  P = []
  for i in range(1, abs(int(coefficients[len(coefficients) - 1]) + 1)):
    if coefficients[len(coefficients) - 1] % i == 0:
      P.append(i)

  syntheticDivisors = []
  for i in P:
    for j in Q:
      k = 0
      temp = 0
      while(k < len(coefficients)):
        temp += coefficients[k] * (i / j) ** (len(coefficients) - 1 - k)
        k += 1
      if temp == 0:
        syntheticDivisors.append(i / j)

      k = 0
      temp = 0
      while(k < len(coefficients)):
        temp += coefficients[k] * (0 - (i / j)) ** (len(coefficients) - k)
        k += 1
      if temp == 0:
        syntheticDivisors.append(0 - (i / j))
  
  print(syntheticDivisors)
  print()
  
  #synthetic division
  for i in syntheticDivisors:
    quotients = []
    temp = 0
    for j in coefficients:
      j = float(j)
      temp += j
      quotients.append(temp)
      temp *= i
    coefficients = quotients
    print(quotients)
    print()

    #ii = len(quotients) - 1
    #while(ii >= 0):
    #  if quotients[ii] == 0.0:
    #    del quotients[ii]
    #  else:
    #    break
    #  ii -= 1

    #need a thing that deletes useless zeros in quotients
    
    if len(quotients) == 3 or len(quotients) == 4: #this line wip
      print('or')

      a = quotients[0]
      b = quotients[1]
      c = quotients[2]
      print((0 - b + (b * b - 4 * a * c) ** .5) / (2 * a))
      print((0 - b - (b * b - 4 * a * c) ** .5) / (2 * a))
      print()

#Given monomials a, b and whole number n, expand (a+b)^n
#wip; rn, it only does this: Given an expression in the form (a + b)^n, expand it
def binomialTheorem():
  a = input("a: ")
  b = input("b: ")
  n = int(input("n: "))

  #render pascals triangle
  pascalstriangle = [[1]]
  for i in range(1, n + 1):
    pascalstriangle.append([1])
    for j in range(1, i):
      pascalstriangle[i].append(int(pascalstriangle[i - 1][j - 1]) + int(pascalstriangle[i - 1][j]))
    pascalstriangle[i].append(1)
  #print(pascalstriangle)

  coefficients = [] #stores the coefficients of the standard form of the expanded expression backwards
  

  
  output = ''
  for i in range(n + 1):
    output += str(pascalstriangle[n][i]) + '(' + a + ')^' + str(n - i) + ' (' + b + ')^' + str(i) + ' + '
  print(output[0 : len(output) - 3])

#Given (a), (h), (k), and (n) in a polynomial 0 = a(x - h)^n + k, find all real zeros
#wip because it only works when n <= 3
def realZerosOfSlopeInterceptFormPolynomials():
  a = float(input('a: '))
  h = float(input('h: '))
  k = float(input('k: '))
  n = float(input('n: '))

  print()
  print(h + (0 - k / a) ** (1 / n))
  print()

#Given 2 zeros from smallest to largest, construct two quartic functions that have only those zeros as real solutions, one in the form y = a(x - h)^4 + k, and one in standard form

def MakeQuarticFunctionGivenOnlyTwoRealZeros():
  smallZero = float(input('smallZero: '))
  bigZero = float(input('bigZero: '))

  print('y = (x - ' + str(bigZero - (bigZero - smallZero) / 2) + ')^4 - ' + str(((bigZero - smallZero) / 2) ** 4))
  print('or')
  print() #put FOIL thing here

#Given a radicand and its index, rationalize the radical
#wip

def rationalizeRoots():
  radicand = float(input('radicand: '))
  index = float(input('index: '))
  temp = radicand
  roots = []

  i = 2
  while i ** index >= temp:
    if temp % (i ** index) == 0:
      temp /= i ** index
      roots.append(i)
    else:
      i += 1

  print(roots)
  print(temp)