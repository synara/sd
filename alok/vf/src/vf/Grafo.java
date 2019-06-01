package vf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Grafo {
	private ArrayList<ArrayList<Vertice>> lst;
	int y;

	public Grafo(int n) {
		lst = new ArrayList<ArrayList<Vertice>>();

		for (int i = 0; i < n; i++) {
			lst.add(new ArrayList<Vertice>());
		}
		y = lst.size();
	}

	// i = inicio; f = final
	public void inserirAresta(int i, int f, int peso) {
		Vertice v = new Vertice();
		v.setId(f);
		v.setValor(peso);
		lst.get(i).add(v);
	}

	// primeira questão
	public void CaminhoMinimo(int s) {
		int[] dist = new int[y];
		int[] pai = new int[y];
		Queue<Integer> fila = new LinkedList<Integer>();

		for (int i = 0; i < y; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		dist[s] = 0;
		pai[s] = s;
		fila.add(s);

		while (!fila.isEmpty()) {
			int x = (int) fila.remove();
			for (Vertice i : lst.get(x)) {
				if (dist[i.getId()] == Integer.MAX_VALUE) {
					dist[i.getId()] = dist[x] + 1;
					pai[i.getId()] = x;
					fila.add(i.getId());
				}
			}
		}
		System.out.println("CAMINHO MÍNIMO");
		for (int i = 0; i < y; i++) {
			System.out.println("Distância: " + dist[i] + ", pai: " + pai[i]);
		}

	}

	// segunda questão
	public void Dijkstra(int v) {
		int[] dist = new int[y];
		int[] pai = new int[y];
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();

		for (int i = 0; i < y; i++) {
			dist[i] = Integer.MAX_VALUE;
			pai[i] = -1;
			heap.add(i);
		}

		dist[v] = 0;
		pai[v] = v;

		while (!heap.isEmpty()) {
			int x = (int) heap.remove();
			for (Vertice i : lst.get(x)) {
				if (dist[i.getId()] > dist[x] + peso(x, i.getId())) {
					dist[i.getId()] = dist[x] + peso(x, i.getId());
					pai[i.getId()] = x;
				}
			}
		}
		System.out.println("DIJKSTRA");
		for (int i = 0; i < y; i++) {
			System.out.println("Distância: " + dist[i] + ", pai: " + pai[i]);
		}

	}

	// auxiliar para segunda questão - dijkstra
	public int peso(int u, int v) {
		for (Vertice vert : lst.get(u)) {
			if (vert.getId() == v) {
				return vert.getValor();
			}
		}
		return 0;
	}

	// terceira questão
	public void Prim() {
		int[] pai = new int[y];
		int menoraresta = -1;
		int vert1 = 0, vert2 = 0;
		
		for (int i = 0; i < y; i++) {
			pai[i] = -1;
		}
		
		pai[0] = 0;
		
		ArrayList<Integer> lstagm = new ArrayList<Integer>();

		lstagm.add(0);

		for (int i = 1; i < y; i++) {
			menoraresta = Integer.MAX_VALUE;

			for (int v : lstagm) {
				for (Vertice u : lst.get(v)) {
					if (!lstagm.contains(u.getId())) {
						if (peso(u.getId(), v) < menoraresta) {
							menoraresta = peso(u.getId(), v);
							vert1 = u.getId();
							vert2 = v;
						}
					}
				}
			}
			lstagm.add(vert1);
			pai[vert1] = vert2;
		}
		System.out.println("PRIM");
		for (int i = 0; i < y; i++) {
			System.out.println("Pai: " + pai[i]);
		}
	}
}
