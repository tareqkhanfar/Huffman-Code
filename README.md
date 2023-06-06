

https://github.com/tareqkhanfar/Huffman-Code/assets/98056148/78173c45-43f2-463c-8508-e2bfa4971d1d

# HuffmanCoding

Huffman coding is a lossless data compression algorithm that uses variable-length codes to represent characters or symbols in a message.
The idea behind Huffman coding is to assign shorter codes to the more frequently occurring symbols in the message and longer codes to the less frequently occurring symbols. 
This results in a more efficient encoding of the message, where the total number of bits required to represent the message is reduced.

The Huffman coding algorithm works as follows:

Calculate the frequency of each symbol in the message.
Create a binary tree where each leaf node represents a symbol and its weight is equal to its frequency.
Combine the two least frequent nodes into a new node, whose weight is equal to the sum of their weights. Repeat this step until all the nodes are combined into a single root node.
Assign a 0 to each edge that leads to a left child and a 1 to each edge that leads to a right child.
Traverse the tree to determine the code for each symbol by concatenating the 0s and 1s on the path from the root to the leaf node representing the symbol.
The resulting Huffman code is a prefix-free code, which means that no code word is a prefix of any other code word. This property ensures that the code can be uniquely decoded without any ambiguity.

https://user-images.githubusercontent.com/98056148/232039545-1fb47ec7-d11b-448e-b905-1ba57f332d11.mp4

![Screenshot (165)](https://user-images.githubusercontent.com/98056148/232039376-283274de-a42f-4cdf-b032-a91597cd4948.png)
![Screenshot (164)](https://user-images.githubusercontent.com/98056148/232039382-5516bb40-e7b3-43fc-a1ac-f085402e657f.png)
![Screenshot (163)](https://user-images.githubusercontent.com/98056148/232039383-097ffb11-33da-4098-bbaf-1f5abda458d1.png)
![Screenshot (162)](https://user-images.githubusercontent.com/98056148/232039384-9b6acaee-e557-417c-b795-b6084d6c0a86.png)
![Screenshot (161)](https://user-images.githubusercontent.com/98056148/232039386-f4b61fd4-3d26-4948-9275-e7c498533dd8.png)
![Screenshot (160)](https://user-images.githubusercontent.com/98056148/232039387-d03bd409-8a15-4185-bdb5-b103343cee57.png)
![Screenshot (159)](https://user-images.githubusercontent.com/98056148/232039389-57aee70e-1d40-47fc-9100-a8154c887b48.png)
![Screenshot (158)](https://user-images.githubusercontent.com/98056148/232039392-ab5ec23d-5ca8-4f34-b74e-3e20b7c4897d.png)
![Screenshot (157)](https://user-images.githubusercontent.com/98056148/232039394-1ef0f6d7-537c-4e3e-a55e-fb76be1ce663.png)





