(ns en.466
  "Custom sort order

   The sort function sorts a collection based on its \"natural ordering.\"
   sort-by allows you to sort a collection based on the natural ordering of the
   result of some function applied to the elements. Your task is to write a
   function sort-with which takes a sequence of elements that define the sort
   order.")

(defn sort-with
  [order coll]
  (let [index (zipmap order (range (count order)))
        sort-fn #(get index %)]
    (sort-by sort-fn coll)))
