(ns en.472
  "Roboto

   A futuristic robot is programmed to take in a sequence of numbers. Each
   number is the distance to travel in a cardinal direction (north, south, east,
   west). It starts facing north at (0, 0), travels straight ahead by the
   distance given in the first number, then turns 90 degrees clockwise, now
   facing east. Then it repeats with the next number. Your job is to calculate
   where it ends up at the end of the sequence.")

(defn- scale
  [[x y] n]
  [(* x n) (* y n)])

(defn- add
  [[x1 y1] [x2 y2]]
  [(+ x1 x2) (+ y1 y2)])

(defn move
  [distances]
  (let [location [0 0]
        cardinals (cycle [[0 1] #_north
                          [1 0] #_east
                          [0 -1] #_south
                          [-1 0] #_west])]
    (->> distances
         (map scale cardinals)
         (reduce add location))))
