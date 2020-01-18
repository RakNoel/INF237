module Main where

solve :: [Integer] -> [Integer]
solve [] = []
solve (a:b:xs) = abs(a - b):(solve xs)

--Input output
readInput = (map read) . words
writeOutput = unlines . (map show)

--Reads in, solves, prints out
main :: IO()
main = interact $ writeOutput . solve . readInput