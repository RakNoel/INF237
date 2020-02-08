//
// Created by oskar on 07/02/2020.
//

#include <vector>

using namespace std;

struct Node {
private:
    short value;
    vector<Node> edges;
    vector<Node> Redges;

public:
    explicit Node(int val) {
        this->value = val;
    }

    void addEdge(Node &n) {
        edges.push_back(n);
    }

    void addREdge(Node &n) {
        Redges.push_back(n);
    }

    vector<Node> GetEdges() {
        return edges;
    }

    int GetValue() {
        return this->value;
    }

    void DeleteEdge(int elem) {
        this->edges.erase(edges.begin() + elem);

    }

    void DeleteREdge(int elem) {
        for (int i = 0; i < Redges.size(); i++)
            if (Redges.at(i).GetValue() == elem)
                this->Redges.erase(Redges.begin() + i);
    }

    void FlipEdges(){
        vector<Node> hold = edges;
        edges = Redges;
        Redges = hold;
    }

    ~Node() = default;
};

class Graph {
private:
    vector<Node> nodes;
public:
    Graph() = default;

    explicit Graph(int m) {
        for (int i = 0; i < m; i++)
            this->AddNode(i);
    };

    void AddNode(int x) {
        this->nodes.emplace_back(x);
    }

    void AddEdge(int a, int b) {
        this->nodes.at(a).addEdge(this->nodes.at(b));
        this->nodes.at(b).addREdge(this->nodes.at(a));
    }

    void FlipGraph() {
        for (int i = 0; i < nodes.size(); i++)
            this->nodes.at(i).FlipEdges();
    }

    vector<Node> GetNodes() {
        return this->nodes;
    }

    size_t EdgeAmount(int a) {
        return this->nodes.at(a).GetEdges().size();
    }

    int FlipEdge(int a, int e) {
        int to = this->nodes.at(a).GetEdges().at(e).GetValue();
        int from = this->nodes.at(a).GetValue();
        nodes.at(from).DeleteEdge(e);
        nodes.at(to).DeleteREdge(from);
        nodes.at(from).addREdge(nodes.at(to));
        nodes.at(to).addEdge(nodes.at(from));
        return to;
    }

    ~Graph() = default;
};