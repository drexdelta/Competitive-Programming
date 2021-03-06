package com.codeforces.icpctraining.learning.dpontrees;

import java.io.*;
import java.util.*;

/**
 * Given a tree T with N nodes, calculate longest path between any two nodes(also known as diameter of tree).
 */
public final class Problem2
{
    public static void main(String[] args)
    {
        InputReader in = new InputReader(System.in);
		Solver solver = new Solver(in);

		solver.solve();
        in.close();
    }

    static class Solver
    {
		int n;
		Node[] tree;
        InputReader in;

		void solve()
		{
			n = in.nextInt();
			tree = new Node[n];

			for (int i = 0; i < n; i++)
				tree[i] = new Node();

			for (int i = 1; i < n; i++)
				tree[in.nextInt()].adj.add(i);	// Parent of i is taken as input. Tree is rooted at 0

			dfs(0);

			for (int i = 0; i < n; i++)
				System.out.println("i : " + i + ", cross : " + tree[i].crossMax + ", nonCross : " + tree[i].nonCrossMax);
		}

		void dfs(int node)
		{
			Node temp = tree[node];

			if (temp.adj.size() == 0)
			{
				temp.crossMax = temp.nonCrossMax = 0;

				return;
			}

			int max, secondMax;
			Iterator<Integer> iterator = temp.adj.iterator();

			max = secondMax = 0;

			while (iterator.hasNext())
			{
				int curr = iterator.next();

				dfs(curr);

				int dist = tree[curr].nonCrossMax;

				if (dist >= max)
				{
					secondMax = max;
					max = dist;
				}
				else if (dist > secondMax)
					secondMax = dist;
			}

			if (temp.adj.size() == 1)
				temp.crossMax = temp.nonCrossMax = max + 1;
			else
			{
				temp.crossMax = max + secondMax + 2;
				temp.nonCrossMax = max + 1;
			}
		}

		class Node
		{
			int crossMax, nonCrossMax;
			List<Integer> adj;

			public Node()
			{
				adj = new ArrayList<>();
			}

		}

        public Solver(InputReader in)
        {
        	this.in = in;
        }

	}

    static class InputReader
    {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream)
        {
            this.stream = stream;
        }

        public int read()
        {
            if (numChars == -1)
                throw new InputMismatchException();

            if (curChar >= numChars)
            {
                curChar = 0;
                try
                {
                    numChars = stream.read(buf);
                } catch (IOException e)
                {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }

            return buf[curChar++];
        }

        public int nextInt()
        {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            int sgn = 1;

            if (c == '-')
            {
                sgn = -1;
                c = read();
            }

            int res = 0;

            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();

                res *= 10;
                res += c & 15;

                c = read();
            } while (!isSpaceChar(c));

            return res * sgn;
        }

        public int[] nextIntArray(int arraySize)
        {
            int array[] = new int[arraySize];

            for (int i = 0; i < arraySize; i++)
                array[i] = nextInt();

            return array;
        }

        public long nextLong()
        {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            int sign = 1;

            if (c == '-')
            {
                sign = -1;

                c = read();
            }

            long result = 0;

            do
            {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();

                result *= 10;
                result += c & 15;

                c = read();
            } while (!isSpaceChar(c));

            return result * sign;
        }

        public long[] nextLongArray(int arraySize)
        {
            long array[] = new long[arraySize];

            for (int i = 0; i < arraySize; i++)
                array[i] = nextLong();

            return array;
        }

        public float nextFloat() // problematic
        {
            float result, div;
            byte c;

            result = 0;
            div = 1;
            c = (byte) read();

            while (c <= ' ')
                c = (byte) read();

            boolean isNegative = (c == '-');

            if (isNegative)
                c = (byte) read();

            do
            {
                result = result * 10 + c - '0';
            } while ((c = (byte) read()) >= '0' && c <= '9');

            if (c == '.')
                while ((c = (byte) read()) >= '0' && c <= '9')
                    result += (c - '0') / (div *= 10);

            if (isNegative)
                return -result;

            return result;
        }

        public double nextDouble() // not completely accurate
        {
            double ret = 0, div = 1;
            byte c = (byte) read();

            while (c <= ' ')
                c = (byte) read();

            boolean neg = (c == '-');

            if (neg)
                c = (byte) read();

            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = (byte) read()) >= '0' && c <= '9');

            if (c == '.')
                while ((c = (byte) read()) >= '0' && c <= '9')
                    ret += (c - '0') / (div *= 10);

            if (neg)
                return -ret;

            return ret;
        }

        public String next()
        {
            int c = read();

            while (isSpaceChar(c))
                c = read();

            StringBuilder res = new StringBuilder();

            do
            {
                res.appendCodePoint(c);

                c = read();
            } while (!isSpaceChar(c));

            return res.toString();
        }

        public boolean isSpaceChar(int c)
        {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String nextLine()
        {
            int c = read();

            StringBuilder result = new StringBuilder();

            do
            {
                result.appendCodePoint(c);

                c = read();
            } while (!isNewLine(c));

            return result.toString();
        }

        public boolean isNewLine(int c)
        {
            return c == '\n';
        }

        public void close()
        {
            try
            {
                stream.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

}

/*

10
0 1 1 2 4 2 0 7 8

11
0 1 1 2 4 2 0 7 8 7

*/
