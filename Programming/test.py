print(__name__)
print(__file__)
import os
import sys
print(os.path.dirname(__file__))
print(os.path.abspath(os.path.dirname(__file__)))
print(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))
