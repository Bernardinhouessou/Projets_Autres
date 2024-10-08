#!/usr/bin/python3.4
# -*- coding: utf-8 -*-
#
#       binarysearchtree.py
#  
#  Copyright 2016 Bernardin.H 
# 
#  ---------------------------------------------------------------------------  
#  This program is free software; you can redistribute it and/or modify
#  it under the terms of the GNU General Public License as published by
#  the Free Software Foundation; either version 2 of the License, or
#  (at your option) any later version.
#  
#  This program is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#  
#  You should have received a copy of the GNU General Public License
#  along with this program; if not, write to the Free Software
#  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
#  MA 02110-1301, USA.to
#  --------------------------------------------------------------------------- 
#  
#
# Programme Binary Search Tree

import os # On importe le module os qui dispose de variables 

          # et de fonctions utiles pour dialoguer avec votre 

          # système d'exploitation


""" Binary Search Tree
Methods - search_element(value), get_max(), get_min(), successor(value),
          insert, delete, values() """

class Node(object):
    def __init__(self, value):
        self.right = None
        self.left = None
        self.parent = None
        self.value = value

    def __repr__(self):
        return "Node with value - %s" % self.value

class BinarySearchTree(object):
    def __init__(self):
        self.root = None
        self.len = 0

    def __len__(self):
        return self.len

    def is_empty(self):
        return self.root == None

    def _inorder(self, node, values):
        if node != None:
            self._inorder(node.left, values)
            values.append(node.value)
            self._inorder(node.right, values)

    def _preorder(self, node, values):
        if node != None:
            values.append(node.value)
            self._preorder(node.left, values)
            self._preorder(node.right, values)

    def _postorder(self, node, values):
        if node != None:
            self._postorder(node.left, values)
            self._postorder(node.right, values)
            values.append(node.value)

    def values(self, reverse = False, order="in"):
        values = []
        if order == "in":
            self._inorder(self.root, values)
        elif order == "pre":
            self._preorder(self.root, values)
        else: #order is post
            self._postorder(self.root, values)
        if reverse:
            return values[::-1]
        return values

    def _search(self, root, value):
        if not root or root.value == value:
            return root
        if value < root.value:
            return self._search(root.left, value)
        else:
            return self._search(root.right, value)

    def search_element(self, value):
        return self._search(self.root, value)

    def _extremes(self, root, find_min = True):
        while (find_min and root.left) or (not find_min and root.right):
            if find_min:
                root = root.left
            else: # find max
                root = root.right
        return root

    def get_min(self):
        """ returns the element with the minimum value """
        return self._extremes(self.root, find_min=True)

    def get_max(self):
        """ returns the element with the maximum value """
        return self._extremes(self.root, find_min=False)

    def successor(self, value):
        """ returns the successor of the element with value - value"""
        node = self.search_element(value)
        if not node:
            return None
        if node.right:
            return self._extremes(node.right, find_min=True)
        parent = node.parent
        while parent and parent.right == node:
            node = parent
            parent = parent.parent
        return parent

    def insert(self, value):
        new_node = Node(value)
        if self.is_empty():
            self.root = new_node
        else:
            node = self.root
            while node and node.value != value:
                parent = node
                if node.value < value:
                    node = node.right
                else:
                    node = node.left
            if node.value == value:
              return
            if parent.value > value:
                parent.left = new_node
            else:
                parent.right = new_node
            new_node.parent = parent
        self.len += 1
        return

    def delete(self, value):
        """ deletes a node from tree with value - value """
        node = self.search_element(value)
        if not node:
            return None
        if not node.left or not node.right:
            node_spliced = node
        else:
            node_spliced = self.successor(node.value)
        if node_spliced.left:
            temp_node = node_spliced.left
        else:
            temp_node = node_spliced.right
        if temp_node:
            temp_node.parent = node_spliced.parent
        if not node_spliced.parent:
            self.root = temp_node
        elif node_spliced == node_spliced.parent.left:
            node_spliced.parent.left = temp_node
        else:
            node_spliced.parent.right = temp_node

        if node != node_spliced:
            node.value = node_spliced.value
        return node_spliced
    
print("\n");
# On met en pause le système (Windows)
os.system("pause")